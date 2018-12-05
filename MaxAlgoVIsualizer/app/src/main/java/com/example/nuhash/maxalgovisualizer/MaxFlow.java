package com.example.nuhash.maxalgovisualizer;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.min;

public class MaxFlow {
    int[][] work_data;
    int n, m, sink, tap;
    String s;
    int ans=0;
    int cost[];
    int visited[];
    MaxFlow(){}
    public MaxFlow(ArrayList<Node_cls> data, int n, int m, int sink, int tap) {
        this.n = n;
        this.m = m;
        this.sink = sink;
        this.tap = tap;
        cost = new int[n + 9];
        visited = new int[n + 9];
        work_data = new int[n + 9][n + 9];
        for (Node_cls nc : data) {
            work_data[nc.from][nc.to] = nc.cap;
        }
    }

    int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(tap);
        cost[tap] = (int) 1e9;
        boolean f = false;
        for (int i = 0; i < n + 9; i++) {
            visited[i] = -1;
        }
        visited[tap] = tap;
        while (!q.isEmpty()) {
            Integer t = q.peek();
            if (t == sink) {
                f = true;
                break;
            }
            q.remove();
            for (int i = 1; i <= n; i++) {
                if (visited[i]==-1 && work_data[t][i] > 0) {
                    q.add(i);
                    visited[i] = t;
                    cost[i] = min(cost[t], work_data[t][i]);
                }
            }
        }
        if (f)
            return cost[sink];
        else
            return 0;
    }
    void path(int now,int flow){
        if(visited[now]==now){
            s+=now+"->";
            return;
        }
        work_data[now][visited[now]]+=flow;
        work_data[visited[now]][now]-=flow;
        path(visited[now],flow);
        s+=now+"";
        if(now!=sink)
            s+="->";
        else s+=" : "+flow;
    }
    ArrayList<String> max_flow(){
        int flow=bfs();
        ans=0;
        ArrayList<String>xd=new ArrayList<>();
        while(flow>0){
            s="";
            ans+=flow;
            path(sink,flow);
            xd.add(s);
            flow=bfs();
        }
        return  xd;
    }
    int get_max_flow()
    {
        return ans;
    }
}
