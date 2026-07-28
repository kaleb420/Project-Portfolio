import numpy as np
from game_logic  import RulesEngine
from AI.baseAI import BaseAI
from collections import defaultdict

class MonteCarloTreeSearchNode():
    def __init__(self, state, parent=None, parent_action=None, heuristic=None):
        self.state = state
        self.parent = parent
        self.parent_action = parent_action
        self.children = []
        self._number_of_visits = 0
        self._results = defaultdict(int)
        self._results[1] = 0
        self._results[-1] = 0
        self._untried_actions = None
        self._untried_actions = self.untried_actions()
        self.heuristic = heuristic  # Optional heuristic function
        return
    
    def untried_actions(self):
        self._untried_actions = self.get_legal_actions()
        return self._untried_actions

    def q(self):
        wins = self._results[1]
        loses = self._results[-1]
        return wins - loses

    def n(self):
        return self._number_of_visits

    def expand(self):
        
        action = self._untried_actions.pop()
        next_state = self.move(action)
        child_node = MonteCarloTreeSearchNode(
            next_state, parent=self, parent_action=action, heuristic=self.heuristic)

        self.children.append(child_node)
        return child_node

    def is_terminal_node(self):
        return self.is_game_over()

    def rollout(self):
        current_rollout_state = self.state
        
        while RulesEngine.check_winner(current_rollout_state) == 0:
            possible_moves = RulesEngine.get_all_moves(current_rollout_state)
            
            if self.heuristic is not None:
                # Use heuristic to guide move selection during rollout
                action = self.heuristic_rollout_policy(possible_moves, current_rollout_state)
            else:
                # Random move selection (pure MCTS)
                action = self.rollout_policy(possible_moves)
            current_rollout_state = BaseAI.simulate_move(self, current_rollout_state, action)
        
        return RulesEngine.check_winner(current_rollout_state)
    
    def backpropagate(self, result):
        self._number_of_visits += 1.
        self._results[result] += 1.
        if self.parent:
            self.parent.backpropagate(result)
    
    def is_fully_expanded(self):
        return len(self._untried_actions) == 0
    
    def best_child(self, c_param=0.1):
        if not self.children:
            return None
        choices_weights = [(c.q() / c.n()) + c_param * np.sqrt((2 * np.log(self.n()) / c.n())) for c in self.children]
        return self.children[np.argmax(choices_weights)]
    
    def rollout_policy(self, possible_moves):
        return possible_moves[np.random.randint(len(possible_moves))]
    
    #Uses the heuristic to select a move during rollout, favoring moves that lead to better heuristic scores
    def heuristic_rollout_policy(self, possible_moves, current_state):
        if not possible_moves:
            return None
        
        # Evaluate each move using the heuristic
        move_scores = []
        for move in possible_moves:
            test_state = BaseAI.simulate_move(self, current_state, move)
            score = self.heuristic(test_state)
            move_scores.append(score)
        
        move_scores = np.array(move_scores)
        
        # Use softmax to convert scores to probabilities (temperature controls randomness)
        # Higher temperature = more random, lower temperature = more greedy
        temperature = 1.0
        max_score = np.max(move_scores)
        exp_scores = np.exp((move_scores - max_score) / temperature)
        probabilities = exp_scores / np.sum(exp_scores)
        
        # Select move based on heuristic probabilities
        selected_index = np.random.choice(len(possible_moves), p=probabilities)
        return possible_moves[selected_index]
    
    def _tree_policy(self):
        current_node = self
        while not current_node.is_terminal_node():
            
            if not current_node.is_fully_expanded():
                return current_node.expand()
            else:
                current_node = current_node.best_child()
        return current_node
    
    def best_action(self, simulation_no=100):
        for i in range(simulation_no):
            v = self._tree_policy()
            reward = v.rollout()
            v.backpropagate(reward)
        
        best = self.best_child(c_param=0.)
        if best is None:
            # If no children exist, expand at least one and return it
            if self._untried_actions:
                return self.expand()
            return None
        return best
    
    def get_legal_actions(self):
        return RulesEngine.get_all_moves(self.state)

    def is_game_over(self):
        #Any number other than 0 means the game is over
        if(RulesEngine.check_winner(self.state) != 0):
            return True
        return False

    def game_results(self):
        #Red is 1, Blue is -1, 0 is draw
        if(RulesEngine.check_winner(self.state) == 1):
            return 1
        elif(RulesEngine.check_winner(self.state) == -1):
            return -1
        return 0

    def move(self, action):
        return BaseAI.simulate_move(self, self.state, action) 
    
    
    