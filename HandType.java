public enum HandType {
    HIGHCARD(1),
    PAIR(2),
    FLUSH(3),
    STRAIGHT(4),
    THREEOFAKIND(5),
    STRAIGHTFLUSH(6);
    
    private final int number;
    HandType(int number){
        this.number = number;
    }
    
    public int getHandTypeValue(){
        return number;
    }
}
