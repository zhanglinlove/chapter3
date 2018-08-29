package org.smart4j.chapter3.controller;

import org.smart4j.chapter3.annotation.Action;
import org.smart4j.chapter3.annotation.Controller;
import org.smart4j.chapter3.bean.Data;
import org.smart4j.chapter3.bean.View;

import java.util.Map;
import java.util.HashMap;

@Controller
public class HelloController {

    @Action("get:/info")
    public View getInfo() {
        View view = new View("hello.jsp");
        view.addModel("name", "张三");
        view.addModel("age", 22);
        view.addModel("sex", "男");
        return view;
    }

    @Action("get:/json")
    public Data getJson() {
        Data data = new Data("hello, welcome to my frameworker.");
        return data;
    }
}
