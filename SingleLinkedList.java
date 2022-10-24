import javax.lang.model.util.ElementScanner6;

public class SingleLinkedList {
    private Node head;
    
    public void add(Object data)
    {
        Node newNode = new Node(data);
        if(head == null)
        head = newNode;
        else
        {
         Node temp = head;
         while(temp.getLink()!=null)
         {
             temp =temp.getLink();
         }
         temp.setLink(newNode);
        }
    }
    public void addTwoData(Object data,Object seconddata)
    {
        Node newNode = new Node(data,seconddata);
        if(head == null)
        head = newNode;
        else
        {
         Node temp = head;
         while(temp.getLink()!=null)
         {
             temp =temp.getLink();
         }
         temp.setLink(newNode);
        }
    }
    public void orderAndAdd(Object data)
    {
     if(head==null)
     {
         Node newNode = new Node(data);
         head = newNode;
     }
     if ((int)data < (int)head.getData()){
        Node newnode = new Node(data);
        newnode.setLink(head);
        head=newnode;
        }
        Node temp =head;
        Node previous =null;
        while(temp!=null&&(Integer)data>(Integer)temp.getData())
        {
            previous=temp;
            temp = temp.getLink();
        }
        if(temp==null)
        {
            Node newNode = new Node(data);
            previous.setLink(newNode);
        }
        else{
            if(previous!=null)
            {
            Node newNode = new Node(data);
            newNode.setLink(temp);
            previous.setLink(newNode);
            }
        }
 
    }
    
    public int size()
    {
        if(head==null)
        {
            return 0;
        }
        else{
            Node temp = head;
            int counter=0;
         while(temp!=null)
         {
             counter++;
             temp =temp.getLink();
         }
         return counter;
        }
    }
    public void display(SingleLinkedList color)
    {
        if(head==null)
        {
        }
        else{
            Node temp = head;
            int index =0;
            while(temp!=null)
         {
             if(color.search(index))
             {
             System.out.print("\u001B[31m" + temp.getData()+" " + "\u001B[0m");
             }
             else
             {
             System.out.print(temp.getData()+" ");
             }
             temp = temp.getLink();
             index++;
         }
        }
    }
    public void displayfirstTen()
    {
        if(head==null)
        {
            System.out.print("Linkedlist is empty");
        }
        else{
            Node temp = head;
            if(size()<10)
            {
                for (int i = 0; i < size(); i++)           
                {   
                    if(temp.getData().equals("Player1"))
                    System.out.println("\u001B[32m"+temp.getData()+"\u001B[0m"+" "+temp.getSeconddata());
                    else if(temp.getData().equals("Player2"))    
                    System.out.println("\u001B[34m"+temp.getData()+"\u001B[0m"+" "+temp.getSeconddata());
                    else
                    System.out.println("\u001B[31m"+temp.getData()+"\u001B[0m"+" "+temp.getSeconddata());
       
                    temp = temp.getLink();
                }
            }
            else
            for (int i = 0; i < 10; i++)           
         {   
             if(temp.getData().equals("Player1"))
             System.out.println("\u001B[32m"+temp.getData()+"\u001B[0m"+" "+temp.getSeconddata());
             else if(temp.getData().equals("Player2"))    
             System.out.println("\u001B[34m"+temp.getData()+"\u001B[0m"+" "+temp.getSeconddata());
             else
             System.out.println("\u001B[31m"+temp.getData()+"\u001B[0m"+" "+temp.getSeconddata());

             temp = temp.getLink();
         }
        }
    }
    public void delete(Object data)
    {
        if(head==null)
        {
            System.out.print("Linkedlist is empty");
        }
        else{
            while((Integer)head.getData()== (Integer)data)
            head=head.getLink();
            Node previous =null;
            Node temp = head;
            while(temp!=null)
            {
                if((Integer)temp.getData()==(Integer)data)
                {
                    previous.setLink(temp.getLink());
                    temp = previous;
                }
                previous =temp;
                temp =temp.getLink();
            }
        }
    }
    void deleteAtIndex(int position)
    {

        if (head == null)
            return;
 

        Node temp = head;
 
        if (position == 0) {
            head = temp.getLink(); 
            return;
        }
 
        for (int i = 0; temp != null && i < position - 1;
             i++)
            temp = temp.getLink();
 
        if (temp == null || temp.getLink() == null)
            return;
 

        Node next = temp.getLink().getLink();
 
        temp.setLink(next);

    }
    public boolean search(Object data)
    {
        if(head==null)
        {
            return false;
        }
        else{
            Node temp =head;
            while(temp!=null)
            {
                if((Integer)temp.getData()==(Integer)data)
                {
                    return true;
                }
                temp =temp.getLink();
                
            }
            return false;
        }
    }
    public void clearList() {
        head = null;
    }
    public Node getHead() {
        return head;
    }
    public void setHead(Node head) {
        this.head = head;
    }
}

