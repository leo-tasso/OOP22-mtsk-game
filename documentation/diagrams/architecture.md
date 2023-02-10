::: mermaid
classDiagram
    Engine *-- "1..*" Minigame 
    Engine *-- "1" View
    SwingView ..|> View
    Minigame *-- "1..*" GameObject
    GameObject *-- "1" InputModel
    GameObject *-- "1" PhysicsModel
    GameObject *-- "1" AspectModel
    AspectModel -- View

    class Engine{
    -processInput() void
    -updateGame(long) void
    -render() void
    }

    class View{
    <<interface>>
    +render() void
    +renderGameOver() void
    }

    class Minigame{
    <<interface>>
    +isGameOver() boolean
    +compute(long) void
    +getGameObjects() List~GameObject~
    }

    class GameObject{
    -inputModel : InputModel 
    -physicsModel : PhysicsModel
    -aspectModel : AspectModel
    }

    class InputModel{
    <<interface>>
    +update(GameObject obj, Input c) void
    }

    class PhysicsModel{
    <<interface>>
    +update(long, GameObject, Minigame) void
    }

    class AspectModel{
    <<interface>>
    +update(GameObject, Drawings) void
    }
:::