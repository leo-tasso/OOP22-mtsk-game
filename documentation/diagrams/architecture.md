::: mermaid
classDiagram
    GameEngine "1:N" o-- Minigame
    Minigame <|.. MinigameImpl
    GameEngine <.. View
    class GameEngine{
    <<interface>>
        processInput()
        updateGame(elapsed)
        render()
    }
    class View{
    <<interface>>
    +render()
    +renderGameOver()
    }
    
    class Minigame {
    <<interface>>
    +processInput()
    +updateGame(elapsed)
    }
    class MinigameImpl {
    -List~GameObjects~ Objects
    +List~GameObjects getObjects()
    }
:::