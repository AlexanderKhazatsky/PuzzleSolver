package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;
import java.util.LinkedList;

public class Solver {
    private SearchNode finalState;
    private MinPQ<SearchNode> searchQueue;

    public Solver(WorldState initial) {
        searchQueue = new MinPQ<>();
        searchQueue.insert(new SearchNode(initial, null, 0));
        solve();
    }

    public int moves() {
        return finalState.currMoves;
    }

    public Iterable<WorldState> solution() {
        SearchNode currNode = finalState;
        LinkedList<WorldState> path = new LinkedList<>();
        while (currNode != null) {
            path.addFirst(currNode.currState);
            currNode = currNode.previous;
        }
        return path;
    }

    private void solve() {
        while (finalState == null) {
            SearchNode bestNode = searchQueue.delMin();
            if (bestNode.currState.isGoal()) {
                finalState = bestNode;
            } else {
                for (WorldState node : bestNode.currState.neighbors()) {
                    SearchNode newNode = new SearchNode(node, bestNode, bestNode.currMoves + 1);
                    if (!newNode.equals(bestNode.previous)) {
                        searchQueue.insert(newNode);
                    }
                }
            }
        }
    }

    private class SearchNode implements Comparable {
        WorldState currState;
        SearchNode previous;
        int currMoves;
        int estimatedSteps;

        SearchNode(WorldState curr, SearchNode prev, int moves) {
            currState = curr;
            previous = prev;
            currMoves = moves;
            estimatedSteps = currState.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(Object o) {
            SearchNode other = (SearchNode) o;
            int myDistance = this.currMoves + this.estimatedSteps;
            int otherDistance = other.currMoves + other.estimatedSteps;
            return myDistance - otherDistance;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            SearchNode other = (SearchNode) o;
            return this.currState.equals(other.currState);
        }

        @Override
        public int hashCode() {
            return currState.hashCode();
        }
    }
}
