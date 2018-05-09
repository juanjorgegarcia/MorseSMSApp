package boys.insper.pro.br.morsesmsapp;

import android.util.Log;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import static java.util.Collections.*;

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

        this.root = new Node();
        this.map = new HashMap<Character, Node>();

        String alphabet = "_etianmsurwdkgohvf_l_pjbxcyzq__54_3___2_______16_______7___8_90";
        Node[] nodes = new Node[alphabet.length()];

        for (int i = 0; i < alphabet.length(); i++) {
            char value = alphabet.charAt(i);
            Node node = new Node(value);
            nodes[i]=node;
            if (value != '_') {
                this.map.put(value, node);
            }
        }
        for (int i = 0; i < alphabet.length(); i++) {
            if (i > 0) {
                nodes[i].setParent(nodes[((i - 1) / 2)]);
            }
            if (i < 31) {
                nodes[i].setLeftChild(nodes[(2 * i) + 1]);
                nodes[i].setRightChild(nodes[(2 * i) + 2]);
            }
        }
        this.root = nodes[0];

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

        LinkedList<String> morse_list = new LinkedList<>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);


        while (!queue.isEmpty()){

            Node current = queue.poll();

            if (current.getValue() != '_'){
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

//    As funcoes abaixo foram feitas para ajudar na construcao do dicionario

//    Funcao que retorna uma lista alfabetica
    public LinkedList<String> getAlpha(){
        LinkedList<String> azList = new LinkedList<>();
        LinkedList<String> CodesList = this.getCodes();


        for (int i = 0; i < CodesList.size() ; i++) {
            azList.add(String.valueOf(this.morseToChar(CodesList.get(i))));
        }

        sort(azList);
        return azList;

    }

//    Funcao que retorna uma lista morse em ordem alfabetica
    public LinkedList<String> getAlphaToMorse(){
        LinkedList<String> azListToMorse = new LinkedList<>();
        LinkedList<String> AlphaList = this.getAlpha();


        for (int i = 0; i < AlphaList.size() ; i++) {
            azListToMorse.add((this.charToMorse((AlphaList.get(i)).charAt(0))));
        }
        return azListToMorse;

    }

//    Funcao que retorna uma lista de strings dos caracteres em ordem do getCodes (pesquisa em largura)
    public LinkedList<String> getCodesToChar(){
        LinkedList<String> codesToCharList = new LinkedList<>();
        LinkedList<String> CodesList = this.getCodes();


        for (int i = 0; i < CodesList.size() ; i++) {
            codesToCharList.add(String.valueOf(this.morseToChar(CodesList.get(i))));
        }

        return codesToCharList;

    }

}
