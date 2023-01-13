package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

public class TaskTableModel extends AbstractTableModel {
    
    String[] columns = {"Nome", "Descri��o", "Prazo", "Tarefa Conclu�da" ,"Editar", "Excluir"};
    List<Task> tasks = new ArrayList<>();   

    @Override
    //fun��o que vai retornar quantas linhas tem a tabela
    public int getRowCount(){
        return tasks.size();
    }

    @Override
    //fun��o que vai retornar quantas colunas tem a tabela
    public int getColumnCount(){
        return columns.length;
    }
    
    @Override
    //fun��o que vai retornar os nomes das colunas para o usuario passada no vetor
    //***nome das fun��es s�o pre-definidas na "AbstractTableModel" portanto so funciona com este nome!***
    public String getColumnName(int columnIndex){
        //retornando a lista de coluna em cada posi��o do index
        return columns[columnIndex];
    }
    
    //fun��o que vai permitir, editar um campo da tabela de lista, que � a tarefaCompletada
    //***nome das fun��es s�o pre-definidas na "AbstractTableModel" portanto so funciona com este nome!***
    public boolean isCellEditable(int rowIndex, int columnIndex){
        if(columnIndex == 3){
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        //verifica se a lista est� vazia
        if(tasks.isEmpty()){
            //retorna o objeto sem nada
            return Object.class;
        }
        //retorna o tipo de dado que est� naquela coluna, por exemplo boolean vai ficar um check "v"
        return this.getValueAt(0, columnIndex).getClass();
    }
    //fun��o para editar um campo recebendo um object e fazendo um cast para outro tipo
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        //eu recebo um object como parametro e cast para (boolean) esse "aValue"
        tasks.get(rowIndex).setCompleted((boolean) aValue);
                
    }

    @Override
    //fun��o que vai retonar o valor referente a linha e coluna passada como parametro
    public Object getValueAt(int rowIndex, int columnIndex) {
        //switch para verificar qual linha que foi passada no parametro
        switch(columnIndex){
            case 0:
                //pega a linha passada no parametro "rowIndex" e verifica o nome que est� nela
                return tasks.get(rowIndex).getName();
            case 1:
                return tasks.get(rowIndex).getDescription();
            case 2:
                //crio uma data formata tradicional
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //uso essa data formatada para formatar o prazo:
                return dateFormat.format(tasks.get(rowIndex).getDeadline());
            case 3:
                return tasks.get(rowIndex).getCompleted();
            case 4:
                return "";
            case 5:
                return "";
            default:
                return "Dados n�o encontrados!";
            
        }
    }
  
    //acessores
    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    
}
