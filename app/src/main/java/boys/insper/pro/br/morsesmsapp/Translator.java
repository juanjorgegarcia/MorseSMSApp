package boys.insper.pro.br.morsesmsapp;

import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
public class Translator {
    // ESTA CLASSE NÃO PODE SER MODIFICADA!
     class Node {
         char value;
         Node parent;
         Node leftChild;
         Node rightChild;

        public Node() {
            this.value = ' ';
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }
        public Node(char value) {
            this.value = value;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        public char getValue() {
            return value;
        }
        public Node getParent() {
            return parent;
        }
        public void setParent(Node parent) {
            this.parent = parent;
        }
        public Node getLeftChild() {
            return leftChild;
        }
        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }
        public Node getRightChild() {
            return rightChild;
        }
        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }


    // ESTE CONJUNTO DE ATRIBUTOS NÃO PODE SER MODIFICADO, OU
    // SEJA, NÃO É PERMITIDO ADICIONAR NEM REMOVER ATRIBUTOS!
     Node root;
     HashMap<Character, Node> map;


    // ESTE CONSTRUTOR DEVE SER PREENCHIDO DE ACORDO COM O ENUNCIADO!
    public Translator() {
        map = new HashMap<Character, Node>();
        root = new Node ();

        Node e = new Node('e');
        root.setLeftChild(e);
        Node t = new Node('t');
        root.setRightChild(t);
        this.map.put(' ',root);


        t.setParent(root);
        Node n = new Node('n');
        t.setLeftChild(n);
        Node m = new Node('m');
        t.setRightChild(m);
        n.setParent(t);
        Node d = new Node('d');
        n.setLeftChild(d );
        Node k = new Node('k');
        n.setRightChild(k );
        d.setParent(n);
        Node b = new Node('b');
        d.setLeftChild( b);
        Node x = new Node('x');
        d.setRightChild( x);
        b.setParent(d);
        Node six = new Node('6');
        b.setLeftChild( six);

        six.setParent(b);

        x.setParent(d);

        k.setParent(n);
        Node c = new Node('c');
        k.setLeftChild(c );
        Node y = new Node('y');
        k.setRightChild( y);
        c.setParent(k);
        y.setParent(k);
        m.setParent(t);
        Node g = new Node('g');
        m.setLeftChild( g);
        Node o = new Node('o');
        m.setRightChild( o);
        g.setParent(m);
        Node z = new Node('z');
        g.setLeftChild( z);
        Node q = new Node('q');
        g.setRightChild( q);
        q.setParent(g);
        z.setParent(g);
        Node seven = new Node('7');
        z.setLeftChild(seven );
        o.setParent(m);
        seven.setParent(z);
        Node oNullLeft = new Node();
        o.setLeftChild(oNullLeft );
        Node oNullRight = new Node();
        o.setRightChild( oNullRight);
        oNullLeft.setParent(o);
        Node eight = new Node('8');
        oNullLeft.setLeftChild(eight );
        oNullRight.setParent(o);
        eight.setParent(oNullLeft);
        Node nine = new Node('9');
        oNullRight.setLeftChild( nine);
        nine.setParent(oNullRight);
        Node zero = new Node('0');
        oNullRight.setRightChild(zero );
        zero.setParent(oNullRight);

        e.setParent(root);
        Node i = new Node('i');
        e.setLeftChild( i);
        Node a = new Node('a');
        e.setRightChild( a);
        i.setParent(e);
        Node s = new Node('s');
        i.setLeftChild(s );
        Node u = new Node('u');
        i.setRightChild(u);
        a.setParent(e);
        Node r = new Node('r');
        a.setLeftChild( r);
        Node w = new Node('w');
        a.setRightChild(w );
        s.setParent(i);
        Node h = new Node('h');
        s.setLeftChild( h);
        Node v = new Node('v');
        s.setRightChild( v);
        u.setParent(i);
        Node f = new Node('f');
        u.setLeftChild( f);
        f.setParent(u);
        Node uNull = new Node();
        u.setRightChild( uNull);
        r.setParent(a);
        Node l = new Node('l');
        l.setParent(r);
        r.setLeftChild(l );
        Node rNull = new Node();
        r.setRightChild(rNull );
        w.setParent(a);
        Node p = new Node('p');
        p.setParent(w);
        w.setLeftChild(p);
        Node j = new Node('j');
        w.setRightChild(j);
        h.setParent(s);

        Node five = new Node('5');
        h.setLeftChild( five);
        five.setParent(h);
        Node four = new Node('4');
        h.setRightChild( four);
        four.setParent(h);
        v.setParent(s);
        Node vNull = new Node();
        v.setLeftChild(vNull );
        Node three = new Node('3');
        v.setRightChild(three);
        three.setParent(v);
        uNull.setParent(u);
        Node two = new Node('2');
        uNull.setRightChild(two );
        two.setParent(uNull);
        rNull.setParent(r);

        Node rNNull = new Node();
        rNull.setRightChild( rNNull);
        j.setParent(w);
        Node one = new Node('1');
        j.setRightChild(one);
        one.setParent(j);

        this.map.put('a',a);
        this.map.put('b',b);
        this.map.put('c',c);
        this.map.put('d',d);
        this.map.put('e',e);
        this.map.put('f',f);
        this.map.put('g',g);
        this.map.put('h',h);
        this.map.put('i',i);
        this.map.put('j',j);
        this.map.put('k',k);
        this.map.put('l',l);
        this.map.put('m',m);
        this.map.put('n',n);
        this.map.put('o',o);
        this.map.put('p',p);
        this.map.put('q',q);
        this.map.put('r',r);
        this.map.put('s',s);
        this.map.put('t',t);
        this.map.put('u',u);
        this.map.put('v',v);
        this.map.put('w',w);
        this.map.put('x',x);
        this.map.put('y',y);
        this.map.put('z',z);
        this.map.put('0',zero);
        this.map.put('1',one);
        this.map.put('2',two);
        this.map.put('3',three);
        this.map.put('4',four);
        this.map.put('5',five);
        this.map.put('6',six);
        this.map.put('7',seven);
        this.map.put('8',eight);
        this.map.put('9',nine);




    }


    // ESTE MÉTODO DEVE SER PREENCHIDO DE ACORDO COM O ENUNCIADO!
    public char morseToChar(String code) {
        Node current = root;
        for(int i = 0; i < code.length(); i++){
            if (code.charAt(i) == '.'){
                current = current.getLeftChild();
            }
            else if (code.charAt(i) == '-'){
                current = current.getRightChild();
            }
            else{


                return ' ';
            }
        }

        return current.getValue();

    }


    // ESTE MÉTODO DEVE SER PREENCHIDO DE ACORDO COM O ENUNCIADO!
    public String charToMorse(char c) {
        Node current = new Node();
        Node previous = new Node();
        String morse = new String();
        current = map.get(c);
        previous = map.get(c);
        while (current.getParent() != null){
            current = current.getParent();
            if(current.getLeftChild() == previous){
                morse += ".";
                previous = previous.getParent();
            }
            else if (current.getRightChild() == previous){
                morse += "-";
                previous = previous.getParent();
            }
        }
        String morse_final="";

        for(int i=morse.length()-1; i>=0; i--)
            morse_final = morse_final + morse.charAt(i);

        return morse_final;
    }


    // ESTE MÉTODO DEVE SER PREENCHIDO DE ACORDO COM O ENUNCIADO!
    public LinkedList<String> getCodes() {
////        private HashMap<Character, Node>   = new HashMap<>();
//        LinkedList<String> codes = new LinkedList();
//
//        Node current = this.root;
//        for (int i = 0; i < this.map.size(); i++) {
////          System.out.print(current.getLeftChild().getValue());
//            System.out.println(codes);
//            if(!codes.contains(this.charToMorse(current.getLeftChild().getValue()))){
//                codes.add(this.charToMorse((current.getLeftChild()).getValue()));
//            }
//            else if (!codes.contains(this.charToMorse(current.getRightChild().getValue()))){
////                System.out.print(current.getLeftChild().getValue());
//                codes.add(this.charToMorse(current.getRightChild().getValue()));
//
//            }
//            else {
//
//                current = current.getLeftChild();
////                System.out.println(current.getValue());
//            }
//
//        }
//    return codes;

        LinkedList<String> morse_list = new LinkedList<>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);


        while (!queue.isEmpty()){

            Node current = queue.poll();

            if (current.getValue() != ' '){
                morse_list.add(this.charToMorse(current.getValue()));
            }

            if (current.getLeftChild() != null) {
                queue.add(current.getLeftChild());
            }

            if (current.getRightChild() != null) {
                queue.add(current.getRightChild());

            }

        }

        return morse_list;
    }
}
