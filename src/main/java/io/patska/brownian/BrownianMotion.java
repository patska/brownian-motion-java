package io.patska.brownian;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class BrownianMotion extends PApplet {

    private static final int TOTAL_MOLECULES = 300;
    private static final int WIDTH = 900;
    private static final int SPEED = 4;
    List<int[]> molecules = createMoleculeList();
    ArrayList<Integer> globalColors = new ArrayList<>();

    public void settings() {
        size(900, 900);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(color(100, 100, 255));
        colors.add(color(255, 153, 204));
        globalColors = colors;
    }

    public void draw() {
        background(64);
        for (int[] molecule : molecules) {
            drawMolecule(molecule);
            molecule = moveMolecule(molecule);
        }
    }

    public int randomMove(int position) {
        int random = SPEED * (generateRandomInteger(3) - 1);
        position += random;
        return min(WIDTH, max(0, position));
    }

    public void drawMolecule(int[] molecule) {
        fill(globalColors.get(molecule[2]));
        noStroke();
        ellipse(molecule[0], molecule[1], 10, 10);
    }

    private int[] moveMolecule(int[] molecule) {
        molecule[0] = randomMove(molecule[0]);
        molecule[1] = randomMove(molecule[1]);
        return molecule;
    }

    private List<int[]> createMoleculeList() {
        List<int[]> molecules = new ArrayList<>();
        for (int i = 0; i < TOTAL_MOLECULES; i++) {
            molecules.add(newMolecule());
        }
        return molecules;
    }

    public int[] newMolecule() {
        int type = generateRandomInteger(2);
        if (type == 0)
            return new int[] { generateRandomInteger(WIDTH / 2), generateRandomInteger(WIDTH), type };
        else
            return new int[] { WIDTH / 2 + generateRandomInteger(WIDTH / 2), generateRandomInteger(WIDTH), type };
    }

    public int generateRandomInteger(int max) {
        return (int) Math.floor(Math.random() * max);
    }

    public static void main(String[] args) {

        String[] appletArgs = new String[] { "io.patska.brownian.BrownianMotion" };
        PApplet.main(appletArgs);
    }
}
