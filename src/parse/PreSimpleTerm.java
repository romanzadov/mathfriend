package parse;


import java.util.List;

public class PreSimpleTerm {

    private List<Character> characters;
    private Type type;

    public static enum Type {
		NUMBER ,
        CONSTANT,
		FUNCTION  ,
		VARIABLE ,
		PARENTHESES ;
	}

    public PreSimpleTerm(List<Character> characters, Type type) {

        if (characters.size() == 0 || type == null) {
            throw new RuntimeException("Tried to construct a preSimpleTerm without characters or type:  characters: "+characters + "  type: "+type);
        }
        try{
            Double.parseDouble(getStringFromList(characters));
        } catch (NumberFormatException e) {
            System.out.println("Unable to parse " + getStringFromList(characters) + " as a double.");
        }

        this.characters = characters;
        this.type = type;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public Type getType() {
        return type;
    }

    private String getStringFromList(List<Character> characters) {
        String string = "";
        for(Character character: characters) {
            string = string + character;
        }
        return string;
    }
}
