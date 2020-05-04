package flashcards;

public class Card {
    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    String name;
    String definition;

    public Card(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }
}
