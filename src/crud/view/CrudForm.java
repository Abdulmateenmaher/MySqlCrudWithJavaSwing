package crud.view;

import crud.dao.DepartmentDeoImp;
import crud.dao.EmployeeDeoImp;
import crud.entity.Department;
import crud.entity.Employee;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CrudForm {
    private JButton search;
    private JButton delete;
    private JTable empTable;
    public JPanel rootPanel;
    private JTextField searchFromDB;
    private JTextField fatherName;
    private JTextField fullName;
    private JTextField TazkiraNo;
    private JButton save;
    private JButton update;
    private JTextField PhoneNo;
    private JTextField whatsappNo;
    private JTextField positionTypeId;
    private JLabel positionTpeID;
    private JTextField salary;
    private JTextField status;
    private JTextArea description;
    private JTextField image;
    private JPanel crudForm;
    private JTextField empId;
    private JButton reset;
    private JComboBox<Department>departmentJComboBox;
    private DepartmentDeoImp departmentDao;
    private EmployeeDeoImp employeeDao;
    private List<Department> departments;
    private List<Employee> students;
    private  DefaultComboBoxModel<Department> departmentDefaultComboBoxModel;
    private DefaultComboBoxModel<Employee> employeeDefaultComboBoxModel;
    private Employee selectedEmployee;

    public CrudForm() throws SQLException, ClassNotFoundException {
        DepartmentDeoImp departmentDao = new DepartmentDeoImp();
        EmployeeDeoImp employeeDao = new EmployeeDeoImp();
       // List<Department> departments = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
       // departments.addAll(departmentDao.findAll());
        employees.addAll(employeeDao.findAll());

        //DefaultComboBoxModel<Department> departmentDefaultComboBoxModel = new DefaultComboBoxModel<>(departments.toArray(new Department[0]));
       // departmentJComboBox.setModel(departmentDefaultComboBoxModel);
        EmployeeTableModel empTableModel=new EmployeeTableModel(employees);
        empTable.setModel(empTableModel);
        empTable.setAutoCreateRowSorter(true);

//        String newDepartment=JOptionPane.showInputDialog(CrudForm,"new department");
//        if(newDepartment!=null && !newDepartment.trim().isEmpty()){
//        Department department=new Department();
//        department.setName(newDepartment);
//        if(departmentDao.addData(department)==1){
//           departments.clear();
//           departments.addAll(departmentDao.findAll());
//           departmentDefaultComboBoxModel.addAll(departments);
//        }
//}

        save.addActionListener(e ->  {
         if(fullName.getText().trim().isEmpty()||fatherName.getText().trim().isEmpty() ){
             JOptionPane.showMessageDialog(crudForm,"Please enter full name ","error",JOptionPane.ERROR_MESSAGE);
         }else {
             Employee employee = new Employee();
             employee.setEmp_name(fullName.getText());
             employee.setEmp_father_name(fatherName.getText());
             employee.setEmp_tazkira_no(TazkiraNo.getText());
             employee.setEmp_phone_no(PhoneNo.getText());
             employee.setEmp_whatsapp_no(whatsappNo.getText());
             employee.setEmp_position_type_id(!Objects.equals(positionTypeId.getText(),"")?Integer.parseInt(positionTypeId.getText()):0);
             employee.setEmp_salary(!Objects.equals(salary.getText(),"")?salary.getText():"0");
             employee.setEmp_status(status.getText());
             employee.setEmp_description(description.getText());
             employee.setEmp_image(image.getText());
             try {
                 if (employeeDao.addData(employee)==1){
                     employees.clear();
                     employees.addAll(employeeDao.findAll());
                     empTableModel.fireTableDataChanged();
                     ResetForm();

                 }
             } catch (SQLException ex) {
                 throw new RuntimeException(ex);
             } catch (ClassNotFoundException ex) {
                 throw new RuntimeException(ex);
             }


         }

        });

        update.addActionListener(e -> {
            if(fullName.getText().trim().isEmpty()||fatherName.getText().trim().isEmpty() ){
                JOptionPane.showMessageDialog(crudForm,"Please enter full name ","error",JOptionPane.ERROR_MESSAGE);
            }else {
                selectedEmployee.setEmp_name(fullName.getText());
                selectedEmployee.setEmp_father_name(fatherName.getText());
                selectedEmployee.setEmp_tazkira_no(TazkiraNo.getText());
                selectedEmployee.setEmp_phone_no(PhoneNo.getText());
                selectedEmployee.setEmp_whatsapp_no(whatsappNo.getText());
                selectedEmployee.setEmp_position_type_id(!Objects.equals(positionTypeId.getText(),"")?Integer.parseInt(positionTypeId.getText()):0);
                selectedEmployee.setEmp_salary(!Objects.equals(salary.getText(),"")?salary.getText():"0");
                selectedEmployee.setEmp_status(status.getText());
                selectedEmployee.setEmp_description(description.getText());
                selectedEmployee.setEmp_image(image.getText());

                try {
                    if(employeeDao.updateData(selectedEmployee)==1){
                        employees.clear();
                        employees.addAll(employeeDao.findAll());
                        empTableModel.fireTableDataChanged();
                        ResetForm();
                    }
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
 // delete employee
        delete.addActionListener(e -> {
            try {
                if (employeeDao.deleteData(selectedEmployee)==1){
                    employees.clear();
                    employees.addAll(employeeDao.findAll());
                    empTableModel.fireTableDataChanged();
                    ResetForm();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
// search employee
        search.addActionListener(e -> {
        try {
            employees.clear();
            employees.addAll(employeeDao.searchByName(searchFromDB.getText()));

            empTableModel.fireTableDataChanged();
            ResetForm();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        });

        empTable.getSelectionModel().addListSelectionListener(e -> {
         if(!empTable.getSelectionModel().isSelectionEmpty()){
             int selectedIndex = empTable.convertRowIndexToModel(empTable.getSelectedRow());
             selectedEmployee=employees.get(selectedIndex);
             if(selectedEmployee != null){
              empId.setText(Objects.toString(selectedEmployee.getEmp_id()));
              fullName.setText(selectedEmployee.getEmp_name());
              fatherName.setText(selectedEmployee.getEmp_father_name());
              TazkiraNo.setText(selectedEmployee.getEmp_tazkira_no());
              PhoneNo.setText(selectedEmployee.getEmp_phone_no());
              whatsappNo.setText(selectedEmployee.getEmp_whatsapp_no());
              positionTypeId.setText(String.valueOf(selectedEmployee.getEmp_position_type_id()));
              salary.setText(String.valueOf(selectedEmployee.getEmp_salary()));
              status.setText(selectedEmployee.getEmp_status());
              description.setText(selectedEmployee.getEmp_description());
              image.setText(selectedEmployee.getEmp_image());
              empId.setVisible(false);
              save.setEnabled(false);
              delete.setEnabled(true);
              reset.setEnabled(true);
              update.setEnabled(true);
             }
         }else {
         }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetForm();

                save.setVisible(true);
            }
        });
    }

// reset input fields of form
    private  void  ResetForm(){
        empId.setText("");
        fullName.setText("");
        fatherName.setText("");
        TazkiraNo.setText("");
        whatsappNo.setText("");
        positionTypeId.setText("");
        salary.setText("");
        status.setText("");
        description.setText("");
        image.setText("");
        PhoneNo.setText("");
        empTable.clearSelection();
        selectedEmployee=null;
        save.setEnabled(true);
    }
// GUI table model
    private  static class  EmployeeTableModel extends AbstractTableModel {
        private List<Employee> employees;
        private final  String[] COLUMNS={"ID","Full Name","Father Name","Tazkira No","Phone No","WhatsApp No",};
        private  EmployeeTableModel(List<Employee> employees) {
            this.employees = employees;
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0,columnIndex)!=null){
                return getValueAt(0,columnIndex).getClass();
            }
            return Object.class;
        }

        @Override
        public int getRowCount() {
            return employees.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> employees.get(rowIndex).getEmp_id();
                case 1 -> employees.get(rowIndex).getEmp_name();
                case 2 -> employees.get(rowIndex).getEmp_father_name();
                case 3 -> employees.get(rowIndex).getEmp_tazkira_no();
                case 4 -> employees.get(rowIndex).getEmp_phone_no();
                case 5 -> employees.get(rowIndex).getEmp_whatsapp_no();
                case 7 -> employees.get(rowIndex).getEmp_position_type_id();
                case 8 -> employees.get(rowIndex).getEmp_salary();
                case 9 -> employees.get(rowIndex).getEmp_status();
                case 10 -> employees.get(rowIndex).getEmp_description();
                case 11 -> employees.get(rowIndex).getEmp_image();
                default -> null;
            };
        }
    }
}
