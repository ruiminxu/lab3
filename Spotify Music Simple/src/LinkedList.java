public class LinkedList
{
    private Node head;
    private int size;

    public void insert(String data)
    {

        if (head == null) {
        head = new Node(data);
        size++;
        return;
    }
        Node pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }

        pointer.next = new Node(data);
        size++;
    }

    public void printList()
    {
        Node pointer = head;
        while(pointer != null)
        {
            System.out.println(pointer.getData());
            pointer = pointer.next;
        }
    }

    public String getIndex(int index)
    {
        Node pointer = head;
        int count = 0;

        while(pointer != null)
        {
            if(index == count)
            {
                return pointer.getData();
            }else{
                count++;
                pointer = pointer.next;
            }
        }

        return "null";
    }

    public int getSize()
    {
        return size;
    }
}
