package com.ragroup.Models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kiolt_000 on 02.02.14.
 */
public class Partner {

    public String id;
    public String name;

    public HashMap<String,Commit> commits = new HashMap<String, Commit>();
    public Partner refer;

    public Partner(){

    }

    public static class Commit{
        //todo:date
        public String status;
        public double pv;
        public double gv;
        public double pgv;
        public double sgv;
        public double bonus;
        public ArrayList<Partner> tree = new ArrayList<Partner>();

    }
}
