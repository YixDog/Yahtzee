public class Node {
    private Object data;
    private Object seconddata;
    private Node link;
    public Node(Object data)
    {
        this.setData(data);
        this.setLink(null);
    }
    public Node(Object data,Object seconddata)
    {
        this.setData(data);
        this.setSeconddata(seconddata);
        this.setLink(null);
    }
    public Node getLink() {
        return link;
    }
    public void setLink(Node link) {
        this.link = link;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Object getSeconddata() {
        return seconddata;
    }
    public void setSeconddata(Object seconddata) {
        this.seconddata = seconddata;
    }
}
