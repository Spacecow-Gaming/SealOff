import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    public static int x;
    public static int health;
    private int dx, direction, width;
    private boolean isHitting, whileHitting;
    public static boolean isBlocking;
    private ImageIcon leftStand, leftBlock, leftPunch;
    private ImageIcon rightStand, rightBlock, rightPunch;

    public Player() {
        x = 400 - width;
        whileHitting = false;
        health = 2000;
        width = 45;
        isHitting = false;
        isBlocking = false;
        //imageIcons START
        leftStand = new ImageIcon(getClass().getResource("leftStand.png"));
        leftPunch = new ImageIcon(getClass().getResource("leftPunch.png"));
        leftBlock = new ImageIcon(getClass().getResource("leftBlock.png"));
        rightStand = new ImageIcon(getClass().getResource("rightStand.png"));
        rightPunch = new ImageIcon(getClass().getResource("rightPunch.png"));
        rightBlock = new ImageIcon(getClass().getResource("rightBlock.png"));
        //imageIcons END
        direction = 1;
        //left = 1, right = 3;
    }

    public int getX() {
        return x;
    }

    public Image getImage() {
        Image returnThing;
        if (direction == 1) {
            if (isHitting && !isBlocking) {
                if (!whileHitting) {
                    returnThing = leftPunch.getImage();
                    if (((x <= Enemy.x && direction == 3) || (x > Enemy.x && direction == 1)) && ((Panel.isAdjacent(x, Enemy.x, width + 10) && direction == 1) || (Panel.isAdjacent(x, Enemy.x, width + 10) && direction == 3))) {
                        if (!Enemy.isBlocking) {
                            Enemy.health -= 2;
                        } else {
                            Enemy.health--;
                        }
                    }
                } else {
                    returnThing = leftStand.getImage();
                }
            } else if (isBlocking && !isHitting) {
                returnThing = leftBlock.getImage();
            } else {
                returnThing = leftStand.getImage();
            }
        } else if (direction == 3) {
            if (isHitting && !isBlocking) {
                if (!whileHitting) {
                    returnThing = rightPunch.getImage();
                    if (((x <= Enemy.x && direction == 3) || (x > Enemy.x && direction == 1)) && ((Panel.isAdjacent(x, Enemy.x, width + 10) && direction == 1) || (Panel.isAdjacent(x, Enemy.x, width + 10) && direction == 3))) {
                        if (!Enemy.isBlocking) {
                            Enemy.health -= 2;
                        } else {
                            Enemy.health--;
                        }
                    }
                } else {
                    returnThing = rightStand.getImage();
                }
            } else if (isBlocking && !isHitting) {
                returnThing = rightBlock.getImage();
            } else {
                returnThing = rightStand.getImage();
            }
        } else {
            returnThing = rightStand.getImage();
        }
        return returnThing;
    }

    public void move() {
        x += dx;
        if (dx < 0) {
            direction = 1;
        } else if (dx > 0) {
            direction = 3;
        }
        basicBounds();
    }


    private void basicBounds() {
        if (x <= 0) {
            x = 0;
        } else if (x >= 400 - width) {
            x = 400 - width;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT && !isHitting && !isBlocking) {
            dx = 1;
        } else if (key == KeyEvent.VK_LEFT && !isHitting && !isBlocking) {
            dx = -1;
        } else if (key == KeyEvent.VK_N) {
            isHitting = true;
        } else if (key == KeyEvent.VK_M) {
            isBlocking = true;
        } else if (key == KeyEvent.VK_SPACE) {
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        } else if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        } else if (key == KeyEvent.VK_N) {
            isHitting = false;
            whileHitting = false;
        } else if (key == KeyEvent.VK_M) {
            isBlocking = false;
        } else if (key == KeyEvent.VK_SPACE) {
            isHitting = false;
        }
    }
}