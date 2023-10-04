import assets.types.Sprite;
import components.Component;
import console.ConsoleUI;
import globaldata.GlobalFlags;
import graphics.Renderer;
import graphics.Window;
import math.types.Vec2;
import parser.Parser;
import parser.RuntimeExecutor;
import scenes.Scene;
import scenes.SceneContainer;
import scenes.ScriptContainer;
import sensors.SensorData;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        GlobalFlags.setDEBUG(true);

        int choice = 0;

        boolean windowOpen = false;

        SceneContainer sceneContainer = new SceneContainer();
        ScriptContainer scriptContainer = new ScriptContainer();

        Parser.init();
        RuntimeExecutor.init();

        SensorData.initSensors();

        choice = ConsoleUI.choose(
                "ComfortGE",
                new String[]{
                        "New project",
                        "Load project"
                },
                '>'
        );

        if (choice == 0) {
            String name = ConsoleUI.prompt(
                    "Choose name for the project: ",
                    '>'
            );

            while (true) {
                choice = ConsoleUI.choose(
                        "Menu: ",
                        new String[]{
                                "Scenes",
                                "Assets",
                                "Components"
                        },
                        '>'
                );

                if (choice == 0) {
                    choice = ConsoleUI.choose(
                            "Scenes: ",
                            new String[]{
                                    "Add scene",
                                    "Change scene",
                                    "Back"
                            },
                            '>'
                    );

                    if (choice == 2) continue;

                    if (choice == 0) {
                        String sceneName = ConsoleUI.prompt(
                                "Scene name: ",
                                '>'
                        );

                        int sceneIndex = Integer.parseInt(ConsoleUI.prompt(
                                "Scene index: ",
                                '>'
                        ));

                        sceneContainer.scenes[sceneIndex] = new Scene(sceneName);

                        sceneContainer.activeScene = sceneIndex;
                    }else{
                        sceneContainer.activeScene = Integer.parseInt(ConsoleUI.prompt(
                                "Scene index: ",
                                '>'
                        ));
                    }
                } else if (choice == 1) {
                    choice = ConsoleUI.choose(
                            "Assets: ",
                            new String[]{
                                    "Import image",
                                    "Import BG",
                                    "Back"
                            },
                            '>'
                    );

                    if (choice == 2) continue;

                    if (choice == 0) {
                        String spriteName = ConsoleUI.prompt(
                                "Enter sprite name: ",
                                '>'
                        );

                        String pathToFile = ConsoleUI.prompt(
                                "Path to image: ",
                                '>'
                        );

                        pathToFile = pathToFile.replace("\"", "");

                        sceneContainer.getActiveScene().imageHolder.sprites.put(
                                spriteName,
                                new Sprite(
                                        ImageIO.read(new File(pathToFile)),
                                        new Vec2(0,0),
                                        0,
                                        spriteName
                                )
                        );
                    } else {
                        String pathToFile = ConsoleUI.prompt(
                                "Path to background: ",
                                '>'
                        );
                    }
                } else if (choice == 2) {
                    choice = ConsoleUI.choose(
                            "Components: ",
                            new String[]{
                                    "Attach component",
                                    "Add component",
                                    "Back"
                            },
                            '>'
                    );

                    if (choice == 2) continue;

                    if (choice == 0) {
                        String componentName  = ConsoleUI.prompt(
                                "Component name: ",
                                '>'
                        );

                        Renderer.lastSelected.components.add(scriptContainer.scriptHolder.components.get(componentName));
                    } else {
                        String componentName = ConsoleUI.prompt(
                                "Component name: ",
                                '>'
                        );

                        Runtime.getRuntime().exec("powershell code");

                        Scanner scanner = new Scanner(System.in);

                        List<String> linesList = new ArrayList<String>();

                        String line = scanner.nextLine();

                        while(!line.equals("eof")) {
                            linesList.add(line);

                            line = scanner.nextLine();
                        }

                        Component component = new Component(componentName, linesList.toArray(new String[0]));

                        scriptContainer.scriptHolder.components.put(componentName, component);
                    }
                }

                if(sceneContainer.activeScene != -1 && !windowOpen) {
                    Window window = new Window(name, 1280, 720);
                    window.sceneContainer = sceneContainer;

                    Renderer.scenes = sceneContainer;

                    windowOpen = true;
                }
            }
        } else {
            ConsoleUI.prompt(
                    "Enter project name: ",
                    '>'
            );
        }
    }
}
