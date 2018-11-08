import java.util.NoSuchElementException;
import java.lang.*;

public class BinaryHeap{
    private int max;
    private int[] data;
    private int size;

    public BinaryHeap(){
        max = 11; //SINCE I AM USING ONE-BASED INDEX POSITION, I AM SETTING MAX (LENGTH OF ARRAY) TO 11.
        data = new int[max];
        data[0] = Integer.MIN_VALUE;
        size = 0;
    }
    public BinaryHeap( int i){
        max = i;
        data = new int[max];
        data[0] = Integer.MIN_VALUE;
        size = 0;
    }
    public int getLChild(int i){
        return i*2;
    }
    public int getRChild(int i){
        return i*2+1;
    }
    public int getParent(int i){
        return i/2;
    }
    private void swap(int p1, int p2){
        int temp;
        temp = data[p1];
        data[p1] = data[p2];
        data[p2] = temp;
    }
    public void doubleSize(){
        int [] newData = new int[data.length*2];
        for(int i = 0; i < size; i++){
            newData[i] = data[i]; 
            //Checking double size function
            //System.out.println("New data size: " + newData.length+ " Data size: " +data.length+ " i: "+ i + " newData[i]: " + newData[i] + " Data[i]: " + data[i]);
        }
        data = newData;
        max = max*2;
    }
    public boolean isleaf(int i){
        //Checking isleaf function.
        //System.out.println("I: " + i + " Size: " + size);
        return ((i > size / 2) && (i <= size));
    }
    public void add(int i){
        if(size == data.length-1){
            doubleSize(); //IF ARRAY IS FULL DOUBLE SIZE
        }
        int current = size++;
        data[size] = i; //SET AT LAST AND CLIMB UP.
        int parent = getParent(current);
        while(data[current] < data[parent]){
            swap(current, parent);
            current = getParent(current);
            parent = getParent(current);
        }
    }

    public int remove(){
        // if(size < 0){
        //     throw new NoSuchElementException("No Such Element");
        // }
        int temp = data[1]; 
        swap(1,size); //TAKE FROM TOP AND MOVE DOWN TO INDEX= SIZE; 
        size--;
        if(size != 0){ 
            shiftdown(1);
        }
        return temp;
    }

    public void shiftdown(int ind){
        int min;
        while( !isleaf(ind)){
            min = getLChild(ind);
            if((min < size) && (data[min] > data[min+1])){
                min = min + 1;
            }
            if(data[ind] <= data[min]){
                return;
            }
            swap(ind,min);
            ind = min;
        }
    }

}
