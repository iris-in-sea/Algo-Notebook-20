/**
 * Key: given input nodes & edges, build map that stores a direct grap 
 *      then BFS the graph to check whether it contains circle, or violate the requirement 
 * 
 * Algo: 1. convert graph (with nodes and edges) into Map<K = node, V = adjList>
 *       2. second Map<K = node, V = indegree> to calculate the indegree of each nodes
 *       3. BFS, put all indegree == 0 nodes into queue, also put them into bfs-result list
 *       4. return: check whether (# of nodes in the list == courses to be taken) if yes, return true
 *(Step2-4 本质: 因为拓扑排序节点入度为 0 时才会存入result, 所以如果存在环，那么有向图的拓扑排序节点个数就会小于图的节点个数)
 */

public class CourseSchedule {
  /**
   * @param numCourses: a total of n courses
   * @param prerequisites: a list of prerequisite pairs
   * @return: true if can finish all courses or false
   */
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (numCourses == 0 || prerequisites.length == 0) {
      return true;
    }
    
    Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);  // course map
    Map<Integer, Integer> indegreeMap = getIndegree(graph);                        // indegree map
    
    // 把所有入度为0的点，放到BFS专用的队列中
    Queue<Integer> queue = new ArrayDeque<>();
    // 初始化拓扑序列为空
    List<Integer> res = new ArrayList<>();
    
    for (int i = 0; i < numCourses; i++) {
      if (!indegreeMap.containsKey(i)) {
        queue.offer(i);
        res.add(i);
      }
    }
    
    // 每次从队列中(queue)拿出一个点放到拓扑序列(BFS list)里，并将该点指向的所有点的入度减1
    while (!queue.isEmpty()) {
      Integer course = queue.poll();  // 如果修掉这门课
      for (Integer nei : couseMap.get(course)) {
        indegreeMap.put(nei, indegreeMap.get(nei) - 1); // 有多少高阶课的 prerequisites 减1了
        
        // 减去1之后入度变为0的点，也放入队列
      
      
    }
  }
  
  private Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    
    for (int i = 0; i < numCourses; i++) {
      int cur = prerequisites[i][0];
      int pre = prerequisites[i][1];
      map.get(pre).add(cur);
    }
    return map;
  }
  
  private Map<Integer, Integer> getIndegree(Map<Integer, List<Integer>> graph) {
    Map<Integer, Integer> indegreeMap = new HashMap<>();
    
    for (int i = 0; i < numCourses; i++) {
      for (Integer nei : graph.get(i)) {
        indegreeMap.put(nei, indegreeMap.getOrDefault(nei, 0) + 1);
      }
    }
    
    return indegreeMap;
  }

}
