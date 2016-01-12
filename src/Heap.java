import java.util.ArrayList;
import java.util.NoSuchElementException;

// This is a Min Heap
// Cost of All actions is Log(n)
public class Heap<Key extends Comparable<Key>> {
    public ArrayList<Key> data;

    public Heap(){
        data = new ArrayList<>();
    }

    public Heap(ArrayList<Key> initialVal) {
        data = initialVal;
        for (int i = (data.size() / 2) - 1; i >= 0 ; i--) {
            heapifyDown(i);
        }
    }

    public void insert(Key k) {
        data.add(k);
        int index = data.size() - 1;
        heapifyUp(index);
    }

    public void changeKey(Key prev, Key next) {
        int idx = find(prev);
        if (idx == -1)
            throw new NoSuchElementException();

        data.set(idx, next);
        if (next.compareTo(prev) < 0)
            heapifyUp(idx);
        else
            heapifyDown(idx);
    }

    public Key extractMin() {
        int size = data.size();
        if (size <= 0)
            throw new NoSuchElementException();

        Key min = data.get(0);

        if (size > 1) {
            data.set(0, data.get(size - 1));
            data.remove(size - 1);
            heapifyDown(0);
        }
        else
            data.remove(size - 1);
        return min;
    }

    public void delete(Key k) {
        int idx = find(k);
        int lastNodeIdx = data.size() - 1;
        Key lastNodeKey = data.get(lastNodeIdx);
        data.set(idx, lastNodeKey);
        data.remove(lastNodeIdx);
        if (lastNodeKey.compareTo(k) > 0)
            heapifyDown(idx);
        else
            heapifyUp(idx);
    }

    private void heapifyUp(int idx) {
        if (idx == 0)
            return;
        int parentIdx = (idx-1) / 2;
        Key parentKey = data.get(parentIdx);
        Key currentKey = data.get(idx);

        if (parentKey.compareTo(currentKey) > 0) {
            Key tmp = parentKey;
            data.set(parentIdx, currentKey);
            data.set(idx, tmp);
            heapifyUp(parentIdx);
        }
    }

    private void heapifyDown(int idx) {
        if (idx >= data.size() / 2)
            return;
        Key nodeKey = data.get(idx);

        int leftId = 2 * idx + 1, rightId = 2 * idx + 2, smallerId;
        Key leftKey = data.get(leftId), rightKey, smallerKey;

        // Finding The Smaller Child
        if (rightId < data.size()) {
            rightKey = data.get(rightId);

            if (rightKey.compareTo(leftKey) > 0) {
                smallerKey = leftKey;
                smallerId = leftId;
            }
            else {
                smallerKey = rightKey;
                smallerId = rightId;
            }
        }
        else {
            smallerKey = leftKey;
            smallerId = leftId;
        }

        // If The Smaller Child is Less than it's parent, Swap!
        if (smallerKey.compareTo(nodeKey) < 0) {
            Key tmp = nodeKey;
            data.set(idx, smallerKey);
            data.set(smallerId, tmp);
            heapifyDown(smallerId);
        }
    }

    private int find(Key k) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).compareTo(k) == 0)
                return i;
        }
        throw new NoSuchElementException();
    }


    /****************** Debugging ******************/
    public void printAll() {
        for (Key k : data)
            System.out.println(k);
    }


    /******************* Test **********************/
    public static void main(String[] args) {
        ArrayList<Double> myData = new ArrayList<>();
        myData.add(2.2);
        myData.add(0.2);
        myData.add(1.2);
        myData.add(1.9);
        Heap myHeap = new Heap(myData);
//        Heap myHeap = new Heap();
        myHeap.printAll();
        System.out.println("****");
        myHeap.insert(1.54);
        myHeap.insert(1.6);
        myHeap.insert(0.9);
        myHeap.insert(0.99);
        myHeap.printAll();
        System.out.println("****");
        myHeap.changeKey(0.99, 1.8);
        myHeap.printAll();
        myHeap.extractMin();
        System.out.println("****");
        myHeap.printAll();
        System.out.println("****");
        myHeap.delete(1.8);
        myHeap.printAll();
    }
}
