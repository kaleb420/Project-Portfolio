from multiprocessing import Process, Queue
from draughts import DraughtsUI
import tkinter as tk
from AI.Advanced_Heuristics.god_ai import GodAI
from AI.Advanced_Heuristics.aggressive_ai import AggressiveAI
from AI.Advanced_Heuristics.safety_ai import SafetyAI
from AI.Heuristics.simple_ai import SimpleAI
from AI.Heuristics.line_ai import LineAI
from AI.Heuristics.edge_ai import EdgeAI
from AI.Heuristics.random_ai import RandomAI
from AI.Heuristics.pawns_ai import PawnsAI
from AI.Heuristics.kings_ai import KingsAI
from AI.Heuristics.safePawns_ai import SafePawnsAI
from AI.Heuristics.safeKings_ai import SafeKingsAI
from AI.Heuristics.promotion_ai import PromotionAI
from AI.Heuristics.threats_ai import ThreatsAI
from AI.Heuristics.centralKings_ai import CentralKingsAI
from AI.Heuristics.centralPawns_ai import CentralPawnsAI
from AI.Advanced_Heuristics.heuristic_mcts_ai import PureMCTSAI, GuidedMCTSAI
#The MCTSAI is fun because it runs all of the heuristics so it doubles the length


def training(ai1, ai2, result_queue):
    root = tk.Tk()
    app = DraughtsUI(root, ai1, ai2)
    root.mainloop()
    
    # After game ends, put result in queue
    result_queue.put({
        'ai1': getattr(ai1, 'name', ai1.__class__.__name__),
        'ai2': getattr(ai2, 'name', ai2.__class__.__name__),
        'winner': app.winner,
        'timing_ai1': {'total': app.total_time_ai1, 'count': app.count_ai1, 'max': app.max_time_ai1, 'min': app.min_time_ai1},
        'timing_ai2': {'total': app.total_time_ai2, 'count': app.count_ai2, 'max': app.max_time_ai2, 'min': app.min_time_ai2}
    })

def run_training(ai_list):
    results = []
    for i, ai1 in enumerate(ai_list):
        opponents = ai_list[i+1:]
        batch_results = []
        processes = []
        result_queue = Queue()
        for ai2 in opponents:
            p = Process(target=training, args=(ai1, ai2, result_queue))
            p.start()
            processes.append(p)
                
        # Wait for all processes for this ai1 to finish
        for p in processes:
            p.join()
            
        while not result_queue.empty():
            batch_results.append(result_queue.get())
        
        results.extend(batch_results)
    
    return results

def write_results_to_file(results, f):
    f.write("Game Results:\n")
    for result in results:
        f.write(f"Blue - {result['ai1']} vs Red - {result['ai2']}: Winner = {result['winner']}\n")
    f.write("\nTiming Results:\n")
    for result in results:
        f.write(f"Blue - {result['ai1']}: {result['runTime_ai1']}\n")
        f.write(f"Red - {result['ai2']}: {result['runTime_ai2']}\n")

# Analyze win rates for each AI and write to file
def analyze_results(results, ai_list):
    win_rates = {}
    timing_info = {}
    for ai in ai_list:
        ai_name = getattr(ai, 'name', ai.__class__.__name__)
        wins = sum(1 for result in results if (result['winner'] == 'Blue' and result['ai1'] == ai_name) or (result['winner'] == 'Red' and result['ai2'] == ai_name))
        losses = sum(1 for result in results if (result['winner'] == 'Red' and result['ai1'] == ai_name) or (result['winner'] == 'Blue' and result['ai2'] == ai_name))
        drawed_to = [result['ai2'] if result['winner'] == 'Draw' and result['ai1'] == ai_name else result['ai1'] for result in results if (result['winner'] == 'Draw' and result['ai1'] == ai_name) or (result['winner'] == 'Draw' and result['ai2'] == ai_name)]
        win_rate = f"{wins} / {wins + losses + len(drawed_to)}" if (wins + losses + len(drawed_to)) > 0 else "N/A"
        losses_to = [result['ai2'] if result['winner'] == 'Red' and result['ai1'] == ai_name else result['ai1'] for result in results if (result['winner'] == 'Red' and result['ai1'] == ai_name) or (result['winner'] == 'Blue' and result['ai2'] == ai_name)]

        win_rates[ai_name] = {
            'win_rate': win_rate,
            'lost_to': losses_to,
            'draws': drawed_to
        }
    
    for ai in ai_list:
        ai_name = getattr(ai, 'name', ai.__class__.__name__)
        total_time = 0.0
        total_count = 0
        max_time = 0.0
        min_time = float('inf')
        for result in results:
            if result['ai1'] == ai_name:
                timing = result['timing_ai1']
                total_time += timing['total']
                total_count += timing['count']
                max_time = max(max_time, timing['max'])
                min_time = min(min_time, timing['min'])
            if result['ai2'] == ai_name:
                timing = result['timing_ai2']
                total_time += timing['total']
                total_count += timing['count']
                max_time = max(max_time, timing['max'])
                min_time = min(min_time, timing['min'])
        average_time = total_time / total_count if total_count > 0 else 0.0
        if min_time == float('inf'):
            min_time = 0.0

        timing_info[ai_name] = {
            'average_time': average_time,
            'longest_time': max_time,
            'shortest_time': min_time
        }

    return win_rates, timing_info   

def write_ai_info(win_rates, timing_info, f):
    f.write("Win Rates:\n")
    for ai_name, info in win_rates.items():
        lost_to = ', '.join(info['lost_to']) if info['lost_to'] else 'None'
        draws = ', '.join(info['draws']) if info['draws'] else 'None'
        f.write(f"{ai_name}: {info['win_rate']}    - Lost to: {lost_to}    - Draws: {draws}\n")

    f.write("\nTiming Information:\n")
    with open("metrics.csv", "w") as metrics:
        metrics.write("AI Name, Mean (seconds), Range (seconds), Total (seconds)\n")
        for ai_name, info in timing_info.items():
            f.write(f"{ai_name}:\n")
            f.write(f"  Average Time: {info['average_time']:.4f} seconds \n")
            f.write(f"  Longest Time: {info['longest_time']:.4f} seconds \n")
            f.write(f"  Shortest Time: {info['shortest_time']:.4f} seconds \n")
            metrics.write(f" {ai_name}," + info['average_time'] + "," + info['longest_time']-info['shortest_time'] + "," + info['total_time'] + "\n")


if __name__ == "__main__":
    temp_list = [GodAI(), SafetyAI(), LineAI(), EdgeAI(), RandomAI(), AggressiveAI(), PawnsAI(), KingsAI(), SafePawnsAI(), SafeKingsAI(), SimpleAI(), PromotionAI(), ThreatsAI(), CentralPawnsAI(), CentralKingsAI()]
    ai_list = temp_list + [PureMCTSAI()]
    for ai in temp_list:
        ai_list.append(GuidedMCTSAI(heuristic=ai))
    results = []
    with open("results.txt", "w") as f:
        for i, ai1 in enumerate(ai_list):
            opponents = ai_list[i+1:]
            batch_results = []
            processes = []
            result_queue = Queue()
            for ai2 in opponents:
                p = Process(target=training, args=(ai1, ai2, result_queue))
                p.start()
                processes.append(p)
                    
            # Wait for all processes for this ai1 to finish
            for p in processes:
                p.join()
                
            while not result_queue.empty():
                batch_results.append(result_queue.get())
            
            results.extend(batch_results)
            
            # After each batch, analyze current results and rewrite the file
            win_rates, timing_info = analyze_results(results, ai_list)
            f.seek(0) #Goes to the beginning of the file
            f.truncate() #Clears the file
            write_ai_info(win_rates, timing_info, f) #Writes the AI info to the file
            f.flush() #Flushes the file buffer to ensure data is written to disk