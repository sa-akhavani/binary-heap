import java.util.ArrayList;

// This is a Min Heap
// Cost of All actions is Log(n)
public class Heap<Key extends Comparable<Key>> {
    public ArrayList<Key> data;

    public Heap(){
        data = new ArrayList<>();
    }
    public Heap(ArrayList<Key> initialVal) {
        data = new ArrayList<>();
    }

    public void insert(Key k) {
        data.add(k);
        int index = data.size() - 1;
        heapifyUp(index);
    }

    public void heapifyUp(int idx) {
        if (idx == 0)
            return;
        int parentIdx = idx / 2;
        Key parentKey = data.get(parentIdx);
        Key currentKey = data.get(idx);

        if (parentKey.compareTo(currentKey) > 0) {
            Key tmp = parentKey;
            data.set(parentIdx, currentKey);
            data.set(idx, parentKey);
            heapifyUp(parentIdx);
        }
    }

    public void printAll() {
        for (Key k : data)
            System.out.println(k);
    }


    /*-------------------------------*/
    /*-------------------------------*/
    /*-------------------------------*/
    /*          Test                 */
    public static void main(String[] args) {
//        ArrayList<Double> myData = new ArrayList<>();

//        Heap myHeap = new Heap(myData);
        Heap myHeap = new Heap();
        myHeap.insert(1.54);
        myHeap.insert(1.6);
        myHeap.insert(0.9);
        myHeap.insert(0.99);

        myHeap.printAll();

    }
}
