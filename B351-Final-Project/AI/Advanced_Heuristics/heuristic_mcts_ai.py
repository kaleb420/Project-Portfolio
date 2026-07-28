import AI.mctsAI as mctsAI

"""
This file adds 2 new AI classes:
- PureMCTSAI: A pure MCTS AI that does not use any heuristic guidance.
- GuidedMCTSAI: An MCTS AI that uses a specific heuristic

To use it simple call:
PureMCTSAI(simulations=100) 
GuidedMCTSAI(heuristic=SomeHeuristicAI(), simulations=150)

~ Simulation values are defaulted to 100 for pure MCTS and 150 for guided MCTS
"""

class MCTSAI:
    def __init__(self, heuristic=None, simulations=100):
        self.simulations = simulations
        # Handle both AI objects and raw functions
        if heuristic is not None:
            if hasattr(heuristic, 'heuristic') and callable(heuristic.heuristic):
                # It's an AI object
                self.heuristic_func = heuristic.heuristic
            elif callable(heuristic):
                # It's a function
                self.heuristic_func = heuristic
            else:
                raise ValueError("Heuristic must be an AI object with heuristic() method or a callable function")
        else:
            self.heuristic_func = None

        # Set the name for identification
        if heuristic is not None:
            self.name = f"GuidedMCTSAI_{heuristic.__class__.__name__}"
        else:
            self.name = "PureMCTSAI"

    #Choose the best move using MCTS with optional heuristic guidance
    def choose_move(self, state):
        root = mctsAI.MonteCarloTreeSearchNode(state, heuristic=self.heuristic_func)
        selected_node = root.best_action(simulation_no=self.simulations)
        
        if selected_node is None:
            return None
        return selected_node.parent_action

# Pure MCTS without heuristic guidance
class PureMCTSAI(MCTSAI):
    def __init__(self, simulations=100):
        super().__init__(heuristic=None, simulations=simulations)

# Heuristic MCTS with a specific heuristic AI
class GuidedMCTSAI(MCTSAI):
    def __init__(self, heuristic, simulations=150):
        super().__init__(heuristic=heuristic, simulations=simulations)
