public class Urban {
    //String list;
    String definition;
    String permalink;
    String thumbs_up;
    String author;
    String word;
    String defid;
    String current_vote;
    String written_on;
    String example;
    String thumbs_down;

    public Urban(String definition, String permalink, String thumbs_up, String author,
                 String word, String defid, String current_vote, String written_on, String example, String thumbs_down){
       // this.list = list;
        this.definition = definition;
        this.permalink = permalink;
        this.thumbs_up = thumbs_up;
        this.author = author;
        this.word = word;
        this.defid = defid;
        this.current_vote = current_vote;
        this.written_on = written_on;
        this.example = example;
        this.thumbs_down = thumbs_down;
    }

    @Override
    public String toString() {
        return " **" + word + "** : " + definition + "\nYou can check " + permalink + " for more details!";
    }
}
