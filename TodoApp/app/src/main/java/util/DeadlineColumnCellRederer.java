package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Task;

/**
 *
 * @author Gabriel
 */
public class DeadlineColumnCellRederer extends DefaultTableCellRenderer{
    //codigo padrão para personalizar uma table com o componente padrão
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
        
        //usando a classe taskModel, pegando a tabela modelo da classe
        TaskTableModel taskModel =  (TaskTableModel) table.getModel();
        //pegando a tarefa da linha selecionada com o "get"
        Task task = taskModel.getTasks().get(row);
        //se o prazo da tarefa for depois do dia atual:
        if(task.getDeadline().after(new Date())){
            label.setBackground(Color.GREEN);
        }
        //se não for depois do dia atual:
        else{
            label.setBackground(Color.RED);   
        }
        //retornando a label personalizada
        return label;
    }
    
}
