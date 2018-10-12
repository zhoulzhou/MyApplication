package com.example.demo.designpattern.statepattern;

public class Context {
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String stateMessage(){
        String state = this.state.getState();
        return state;
    }
}
