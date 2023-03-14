::: mermaid
classDiagram
    Engine *-- "1..*" Minigame
    ControllerImpl *-- "1" View
    Controller "1" --* View
    SwingView ..|> View
    JavaFxView ..|> View
    Minigame *-- "1..*" GameObject
    GameObject *-- "1" AspectModel
    GameObject *-- "1" InputModel
    GameObject *-- "1" PhysicsModel
    View *-- Drawings
    Controller ..|> ControllerImpl
    ControllerImpl *-- Engine
    class Controller{
        <<interface>>
    }

    class Engine{
    <<interface>>
    -processInput() void
    -updateGame(long) void
    -render() void
    }
    
    class Drawings{
        <<interface>>
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
//TODO Correct method names.
