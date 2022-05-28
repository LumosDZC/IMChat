package com.hx.imchat.home.chatlist;

import java.util.ArrayList;
/**
 * @author: Hx
 * @date: 2022年04月08日 22:49
 *
 */
public class ChatListInfo {
    //生成临时数据，可以删掉
    public static ArrayList<ChatInfo> getTempEvaluationInfo() {
        ArrayList<ChatInfo> list = new ArrayList<>();
        list.add(new ChatInfo(1, "title","describe"));
        list.add(new ChatInfo(2, "title","describe"));
        list.add(new ChatInfo(3, "title","describe"));
        return list;
    }
}
