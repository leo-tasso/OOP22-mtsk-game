::: mermaid
classDiagram
    Engine --* ModelInterface 
    Engine --* ViewInterface 
    Engine ..|> ViewObserver
    View --> ViewObserver
    View ..|> ViewInterface
    Model ..|> ModelInterface

    class Engine{
    processInput()
    update()
    render()
    }

    class ViewInterface{
    <<interface>>
    render()
    renderGameOver()
    }

    class ModelInterface{
    <<interface>>
    }

    class ViewInterface{
    <<interface>>
    }

    class ViewObserver{
    <<interface>>
    }
:::