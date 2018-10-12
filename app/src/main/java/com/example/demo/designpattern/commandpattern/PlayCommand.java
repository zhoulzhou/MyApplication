package com.example.demo.designpattern.commandpattern;

public class PlayCommand implements Command {
    private AudioPlayer myAudio;

    public PlayCommand(AudioPlayer audioPlayer) {
        myAudio = audioPlayer;
    }

    /**
     * 执行方法
     */
    @Override
    public void execute() {
        myAudio.play();
    }
}
