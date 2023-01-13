/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Task;

/**
 *
 * @author Gabriel
 */
public class ButtonColumnCellRederer extends DefaultTableCellRenderer {
    //codigo padrão para personalizar uma table com o componente padrão
    private String buttonType;

    public ButtonColumnCellRederer(String buttonType) {
        this.buttonType = buttonType;
    }
    
    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    //fazendo a sobre escrita do metodo que ta na classe pai, usando o override e o mesmo nome do metodo
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int col){
        //criando uma label
        JLabel label;
        //pegando o componente padrão da classe pai e fazendo um cast pra guardar na label
        label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        //***Label vai ser o componente personalizador***
        //colocando os elementos da tabela centralizados
        label.setHorizontalAlignment(CENTER);
        //pegando o icone no caminho que ta na pasta resource do aplicativo com o nome do botão definido no construtor
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/" + buttonType + ".png")));
        

        return label;
        
    }
}
