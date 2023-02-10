::: mermaid
classDiagram
    Engine  "1:N" o-- Minigame
    Minigame <|.. WhacAMole
    Minigame <|.. CatchTheSquare
    Minigame <|.. DodgeATriangle
    Minigame <|.. FlappyBirdAlike
    Engine <|.. Score

    class Engine{
    -minigameList : List~Minigame~
    }
    class Score {
    <<interface>>
    +getScore() Score
    }
    class Minigame{
    <<interface>>
    +isGameOver() boolean
    +compute(long) void
    +getGameObjects() List~GameObject~
    }
    class WhacAMole{
    <<interface>>
    }
    class CatchTheSquare{
    <<interface>>
    }
    class FlappyBirdAlike{
    <<interface>>
    }
    class DodgeATriangle{
    <<interface>>
    }
:::