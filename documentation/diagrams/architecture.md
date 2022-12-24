::: mermaid
classDiagram
    MtskGame o-- GameEngine
    GameEngine "1:N" o-- Minigame
    Minigame o-- MinigameModel
    GameEngine <.. View
    class MtskGame{
    }
    class GameEngine{
    <<interface>>
        +processInput()
        +updateGame(elapsed)
        +render()
    }
    class View{
    <<interface>>
    +render()
    }
    
    class Minigame {
    <<interface>>
    +processInput()
    +updateGame(elapsed)
    +render()
    }
    class MinigameModel {
    <<interface>>
    List~GameObjects~ Objects
    }
:::