package agh.ics.project2;

public enum Signs {
    X,
    Nothing,
    O;

    public Signs getNextSign(){
        if (this == Signs.X){
            return Signs.O;
        }else{
            return Signs.X;
        }
    }

    public String getText(){
        if (this == Signs.Nothing){
            return " ";
        }
        return super.toString();
    }

}
