package com.example.demo.designpattern.commandpattern;

public class RewindCommand implements Command {

    private AudioPlayer myAudio;

    public RewindCommand(AudioPlayer audioPlayer) {
        myAudio = audioPlayer;
    }

    @Override
    public void execute() {
        myAudio.rewind();
    }
}
