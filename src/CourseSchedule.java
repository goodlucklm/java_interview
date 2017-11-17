import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class CourseSchedule {
    private Map<Integer, List<Integer>> myGraph;

    private boolean visitVertice(int index, Integer[] vertices, boolean[] visited, Stack<Integer> stack, boolean[] finished) {
        if (finished[index]) return true;
        if (visited[index]) return false;
        visited[index] = true;
        if (myGraph.get(vertices[index]).size() != 0) {
            for (Integer n : myGraph.get(vertices[index])) {
                int i = Arrays.asList(vertices).indexOf(n);
                if (!visitVertice(i, vertices, visited, stack, finished)) return false;
            }
        }
        finished[index] = true;
        stack.push(vertices[index]);
        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        myGraph = new HashMap<>();

        // build edges
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];

            // init map entry if necessary
            if (!myGraph.containsKey(course))
                myGraph.put(course, new ArrayList<>());

            if (!myGraph.containsKey(prerequisite))
                myGraph.put(prerequisite, new ArrayList<>());

            // add edge
            myGraph.get(course).add(prerequisite);
        }

        // sort the courses
        Stack<Integer> stack = new Stack<>();
        boolean[] visitStarted = new boolean[myGraph.size()];
        Arrays.fill(visitStarted, Boolean.FALSE);
        boolean[] visitEnded = new boolean[myGraph.size()];
        Arrays.fill(visitEnded, Boolean.FALSE);
        Object[] objs = myGraph.keySet().toArray();
        Integer[] vertices = new Integer[objs.length];
        for (int i = 0; i < objs.length; i++)
            vertices[i] = (Integer)objs[i];

        for (int i = 0; i < vertices.length; i++) {
            if (!visitVertice(i, vertices, visitStarted, stack, visitEnded))
                return false;
        }

        for (Integer s : stack)
            System.out.println(s);
        return true;
    }

    public static void main(String[] args)  {
        CourseSchedule cs = new CourseSchedule();
        int[][] schedule = {{0,1},{0,2},{3,4},{2,3},{1,0}};
        cs.canFinish(3,schedule);

    }

}
