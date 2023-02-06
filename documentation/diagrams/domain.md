::: mermaid
classDiagram
    Engine  "1:N" o-- Minigame
    Minigame <|.. WhacAMole
    Minigame <|.. CatchTheSquare
    Minigame <|.. DodgeATriangle
    Minigame <|.. FlappyBirdAlike
    Engine <|.. Score

    class Engine{
    + addMinigame(Minigame)
    }
    class Score {
    <<interface>>
    + getScore()
    }
    class Minigame{
    <<interface>>
    + isOver()
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