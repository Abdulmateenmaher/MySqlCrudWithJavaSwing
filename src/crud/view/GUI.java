package crud.view;

import crud.dao.AttendanceDaoImp;
import crud.dao.DepartmentDeoImp;
import crud.dao.EmployeeDeoImp;
import crud.entity.Attendance;
import crud.entity.Department;
import crud.entity.EmployeeJcomboxListForAttendance;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GUI {
    private JButton search;
    private JButton deleteEmployee;
    private JTable empTable;
    public JPanel rootPanel;
    private JTextField searchFromDB;
    private JTextField fatherName;
    private JTextField fullName;
    private JTextField TazkiraNo;
    private JButton saveEmployee;
    private JButton update;
    private JTextField PhoneNo;
    private JTextField whatsappNo;
    private JTextField departmentName;
    private JLabel positionTpeID;
    private JTextField salary;
    private JTextField status;
    private JTextField description;
    private JTextField image;
    private JPanel empCRUD_Form;
    private JTextField empId;
    private JButton reset;
    private JButton chooseFileButton;
    private JComboBox comboxDep;
    private JTextField depname;
    private JTextField depDesc;
    private JButton saveDepartment;
    private JButton deleteDepartment;
    private JTable tableDep;
    private JTable attendanceTable;
    private JScrollPane Attendancepane;
    private JPanel AttendanceTable;
    private JPanel Deprtment;
    private JPanel Attendance;
    private JScrollPane atteScrollPane;
    private JLabel makePresent;
    private JComboBox empCombox;
    private JLabel todayDate;
    private JButton addToTodayAtt;
    private JCheckBox isPresentCheckBox;
    private JComboBox attdepcombox;
    private JComboBox<Department>departmentJComboBox;
    private DepartmentDeoImp departmentDao;
    private EmployeeDeoImp employeeDao;
    private  DepartmentDeoImp departmentDeoImp;
    private List<Department> departments;
    private List<crud.entity.Employee> students;
    private  DefaultComboBoxModel<Department> departmentDefaultComboBoxModel;
    private  JComboBox<Attendance> attendanceComboBox;
    private DefaultComboBoxModel<crud.entity.Employee> employeeDefaultComboBoxModel;
    private crud.entity.Employee selectedEmployee;
    private  String fileImage;
    private  DefaultComboBoxModel<Department> empDepartmentComboxModel;
    private  DefaultComboBoxModel<Department> attDepartmentComboxModel;
    private  Department selectedDepartment;
    private  String empIdFromJcomboxAtt;
    private  String depIdFromJcomboxAtt;
    private  boolean addEmpTOAttIsPresent;
    private  String imagePath;
// Constructor
    public GUI() throws SQLException, ClassNotFoundException, FileNotFoundException {
        DepartmentDeoImp departmentDao = new DepartmentDeoImp();
         AttendanceDaoImp attendanceDaoImp=new AttendanceDaoImp();
        List<crud.entity.Employee> employees = new ArrayList<>();
        List<crud.entity.Department> departments = new ArrayList<>();
        List<crud.entity.Attendance> attendances = new ArrayList<>();
        EmployeeDeoImp employeeDao = new EmployeeDeoImp();
        List<EmployeeJcomboxListForAttendance> jcomboxEmployees = new ArrayList<>();

// *********************** add departments to list ***************************
        try {
            departments.addAll(departmentDao.findAll());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

// *********************** add attendances to list ***************************
        attendances.addAll(attendanceDaoImp.findAll());
        employees.addAll(employeeDao.findAll());

// ************************* make array for employees jCombox model for attendance *******************
        jcomboxEmployees.addAll(employeeDao.employeesJcomboxList());
        employeeDefaultComboBoxModel=new DefaultComboBoxModel(employeeDao.employeesJcomboxList().toArray());
        empCombox.setModel(employeeDefaultComboBoxModel);
// ************************* make array for department jCombox model *******************

        empDepartmentComboxModel =new DefaultComboBoxModel(departments.toArray());
        attDepartmentComboxModel = new DefaultComboBoxModel(departments.toArray());
        comboxDep.setModel(empDepartmentComboxModel);
        attdepcombox.setModel(attDepartmentComboxModel);
// ****************** show Employee *********************************
        EmployeeTableModel employeeTableModel=new EmployeeTableModel(employees);
        empTable.setModel(employeeTableModel);
        empTable.setAutoCreateRowSorter(true);
// ****************** show Departments *********************************
        DepartmentTableModel departmentTableModel= new DepartmentTableModel(departments);
        tableDep.setModel(departmentTableModel);
        tableDep.setAutoCreateRowSorter(true);
// ****************** show Attendance *********************************
        AttendanceTableModel attendanceTableModel=new AttendanceTableModel(attendances);
        attendanceTable.setModel(attendanceTableModel);
        attendanceTable.setAutoCreateRowSorter(true);

//****************** Save Employee *********************************
        saveEmployee.addActionListener(e ->  {
            if(fullName.getText().trim().isEmpty()||fatherName.getText().trim().isEmpty() ){
                JOptionPane.showMessageDialog(empCRUD_Form,"full name, father name, and department no are important ","error",JOptionPane.ERROR_MESSAGE);
            }else {
                crud.entity.Employee employee = new crud.entity.Employee();
                employee.setEmp_name(fullName.getText());
                employee.setEmp_father_name(fatherName.getText());
                employee.setEmp_tazkira_no(TazkiraNo.getText());
                employee.setEmp_phone_no(PhoneNo.getText());
                employee.setEmp_whatsapp_no(whatsappNo.getText());
                String depId=comboxDep.getSelectedItem().toString();
                depId= depId.substring(0, depId.indexOf(" "));
                employee.setEmp_departmentName(depId);
                employee.setEmp_salary(!Objects.equals(salary.getText(),"")?salary.getText():"0");
                employee.setEmp_status(status.getText());
                employee.setEmp_description(description.getText());
                employee.setEmp_image(fileImage);
                System.out.println("lshdflshglshgslghslghslghslghghsjghsjgh");
               // employee.setImagePath(imagePath);
                //System.out.println(fileImage.getAbsolutePath());
                EmployeeDeoImp employeeDeoImp=new EmployeeDeoImp();
                try {
                    if (employeeDeoImp.addData(employee)==1){
                        employees.clear();
                        employees.addAll(employeeDao.findAll());
                        employeeTableModel.fireTableDataChanged();
                        resetEmployeeForm();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException | FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

//****************** Save Department *********************************
        saveDepartment.addActionListener(e ->  {
            if(depname.getText().trim().isEmpty()||depDesc.getText().trim().isEmpty() ){
                JOptionPane.showMessageDialog(empCRUD_Form,"department name, and description are important ","error",JOptionPane.ERROR_MESSAGE);
            }else {
                crud.entity.Department department = new crud.entity.Department();
                department.setName(depname.getText());
                department.setDescription(depDesc.getText());
                try {
                    DepartmentDeoImp departmentDeoImp=new DepartmentDeoImp();
                    if (departmentDeoImp.addData(department)==1){
                        departments.clear();
                        departments.addAll(departmentDao.findAll());
                        departmentTableModel.fireTableDataChanged();
                        resetEmployeeForm();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

//****************** Save to today attendance *********************************
        addToTodayAtt.addActionListener(e ->  {
            String empId=empCombox.getSelectedItem().toString();
            System.out.println(empId);
                empId= empId.substring(0, empId.indexOf(" "));


            String depId=attdepcombox.getSelectedItem().toString();
                depId= depId.substring(0, depId.indexOf(" "));
            System.out.println(depId);

                crud.entity.Attendance attendance=new Attendance();
               // attendance.setEmployeeName();
                attendance.setEmployeeId(Integer.valueOf(Integer.valueOf(empId)));
                attendance.setDepartmentId(Integer.valueOf(Integer.valueOf(depId)));
                System.out.println("************************   "+depId);
                attendance.setPresent(addEmpTOAttIsPresent);
                long millis=System.currentTimeMillis();
                java.sql.Date date=new java.sql.Date(millis);
                attendance.setDate(date.toString());
                attendance.setPresent(addEmpTOAttIsPresent);
                AttendanceDaoImp attendanceDao=new AttendanceDaoImp();
                try {
                    if (attendanceDao.addData(attendance)==1){
                        attendances.clear();
                        attendances.addAll(attendanceDao.findAll());
                        attendanceTableModel.fireTableDataChanged();
                        resetEmployeeForm();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException | FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

        });

// ****************** update Employee *********************************
        update.addActionListener(e -> {
            if(fullName.getText().trim().isEmpty()||fatherName.getText().trim().isEmpty() ){
                JOptionPane.showMessageDialog(empCRUD_Form,"Please enter full name ","error",JOptionPane.ERROR_MESSAGE);
            }else {
                selectedEmployee.setEmp_name(fullName.getText());
                selectedEmployee.setEmp_father_name(fatherName.getText());
                selectedEmployee.setEmp_tazkira_no(TazkiraNo.getText());
                selectedEmployee.setEmp_phone_no(PhoneNo.getText());
                selectedEmployee.setEmp_whatsapp_no(whatsappNo.getText());
                String depId=comboxDep.getSelectedItem().toString();
                depId= depId.substring(0, depId.indexOf(" "));
                selectedEmployee.setEmp_departmentName(depId);
                //selectedEmployee.setEmp_departmentName(String.valueOf(depID));
                selectedEmployee.setEmp_salary(!Objects.equals(salary.getText(),"")?salary.getText():"0");
                selectedEmployee.setEmp_status(status.getText());
                selectedEmployee.setEmp_description(description.getText());
              //  selectedEmployee.setEmp_image(fileImage);
                try {
                    if(employeeDao.updateData(selectedEmployee)==1){
                        employees.clear();
                        employees.addAll(employeeDao.findAll());
                        employeeTableModel.fireTableDataChanged();
                        resetEmployeeForm();
                    }
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

// ****************** delete Employee *********************************  // delete employee
        deleteEmployee.addActionListener(e -> {
            try {
                if (employeeDao.deleteData(selectedEmployee)==1){
                    employees.clear();
                    employees.addAll(employeeDao.findAll());
                    employeeTableModel.fireTableDataChanged();
                    attendanceTableModel.fireTableDataChanged();
                    resetEmployeeForm();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

// ****************** delete department *********************************  // delete employee
        deleteDepartment.addActionListener(e -> {
            try {
                if (departmentDao.deleteData(selectedDepartment)==1){
                    departments.clear();
                    departments.addAll(departmentDao.findAll());
                    departmentTableModel.fireTableDataChanged();
                    attendanceTableModel.fireTableDataChanged();
                    resetDepartmentForm();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

// ****************** search Employee ********************************* ** search employee
        search.addActionListener(e -> {
        try {
            if(searchFromDB.getText()!=""){
                employees.clear();
                employees.addAll(employeeDao.searchByName(searchFromDB.getText()));
                employeeTableModel.fireTableDataChanged();
                resetEmployeeForm();
            }else {
                employees.clear();
                employees.addAll(employeeDao.findAll());
                employeeTableModel.fireTableDataChanged();
                resetDepartmentForm();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        });

//****************** select Employee Table *********************************
        empTable.getSelectionModel().addListSelectionListener(e -> {
         if(!empTable.getSelectionModel().isSelectionEmpty()){
             int selectedIndex = empTable.convertRowIndexToModel(empTable.getSelectedRow());
             selectedEmployee=employees.get(selectedIndex);
             if(selectedDepartment != null){
              empId.setText(Objects.toString(selectedEmployee.getEmp_id()));
              fullName.setText(selectedEmployee.getEmp_name());
              fatherName.setText(selectedEmployee.getEmp_father_name());
              TazkiraNo.setText(selectedEmployee.getEmp_tazkira_no());
              PhoneNo.setText(selectedEmployee.getEmp_phone_no());
              whatsappNo.setText(selectedEmployee.getEmp_whatsapp_no());
              //departmentName.setText(String.valueOf(selectedEmployee.get_departmentName()));
              salary.setText(String.valueOf(selectedEmployee.getEmp_salary()));
              status.setText(selectedEmployee.getEmp_status());
              description.setText(selectedEmployee.getEmp_description());
              //image.setText(selectedEmployee.getEmp_image().getAbsolutePath());//
              empId.setVisible(false);
              saveEmployee.setEnabled(false);
              deleteEmployee.setEnabled(true);
              reset.setEnabled(true);
              update.setEnabled(true);
             }
         }else {
         }
        });

// ****************** select Departments Table ******************************
        tableDep.getSelectionModel().addListSelectionListener(e -> {
            if(!tableDep.getSelectionModel().isSelectionEmpty()){
                int selectedIndex = tableDep.convertRowIndexToModel(tableDep.getSelectedRow());
                selectedDepartment=departments.get(selectedIndex);
                if(selectedDepartment != null){
                    depname.setText(Objects.toString(selectedDepartment.getName()));
                    depDesc.setText(selectedDepartment.getDescription());
                }
            }else {
            }
        });

//****************** reset Listner Employee *********************************
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetEmployeeForm();

                saveEmployee.setVisible(true);
            }
        });

//****************** choose file button **************************************
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser=new JFileChooser();
                FileNameExtensionFilter fileNameExtensionFilter=new FileNameExtensionFilter("image file","jpg","png","jpeg");
                fileChooser.setFileFilter(fileNameExtensionFilter);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int choose=fileChooser.showOpenDialog(empCRUD_Form);
                if(choose==JFileChooser.APPROVE_OPTION){
                    File selectedFile=new File(fileChooser.getSelectedFile().getAbsolutePath());
                    System.out.println("file path: "+selectedFile);
                    fileImage=selectedFile.getName();
                    System.out.println(fileImage);
                    imagePath=selectedFile.getAbsolutePath();
                }
            }
        });

// ******************* add data for Is Present Employee Variable**************
        isPresentCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             if(isPresentCheckBox.isSelected()){
                 addEmpTOAttIsPresent=true;
             }else {
                 addEmpTOAttIsPresent=false;

             }
            }
        });
    }// End of GUI Constructor

//****************** Reset Form of Employee *********************************
    private  void  resetEmployeeForm(){
        empId.setText("");
        fullName.setText("");
        fatherName.setText("");
        TazkiraNo.setText("");
        whatsappNo.setText("");
        salary.setText("");
        status.setText("");
        description.setText("");
        PhoneNo.setText("");
        empTable.clearSelection();
        selectedEmployee=null;
        saveEmployee.setEnabled(true);
    }
//****************** Reset Form of Department *********************************
    private  void  resetDepartmentForm(){
        depname.setText("");
        depDesc.setText("");
        tableDep.clearSelection();
        selectedDepartment=null;
        saveDepartment.setEnabled(true);
    }
// ************************** reset department comobox ***********************
private  void resetDepartmentCombox(){

}

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

//************************ Employee GUI table model*************************
    private  static class  EmployeeTableModel extends AbstractTableModel {
        private final List<crud.entity.Employee> employees;
        private final  String[] COLUMNS={"ID","Full Name","Father Name","Tazkera No","Phone No","WhatsApp No","Department Name", "Salary","status","Description","image"};
        private  EmployeeTableModel(List<crud.entity.Employee> employees) {
            this.employees = employees;
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0,columnIndex)!=null){
                return getValueAt(0, columnIndex).getClass();
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
                case 6 -> employees.get(rowIndex).get_departmentName();
                case 7 -> employees.get(rowIndex).getEmp_salary();
                case 8 -> employees.get(rowIndex).getEmp_status();
                case 9 -> employees.get(rowIndex).getEmp_description();
                case 10 -> employees.get(rowIndex).getEmp_image();//
                default -> null;
            };
        }

    }

//*********************Department GUI Table Model*******************************
    private  static  class  DepartmentTableModel extends AbstractTableModel{
        private  final  List<Department>departments;
        private  final  String[] COLUMNS={"ID","Name","Department"};
        private  DepartmentTableModel(List<Department> departments) {
            this.departments=departments;
        }
        @Override
        public int getRowCount() {
            return departments.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }


        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> departments.get(rowIndex).getId();
                case 1 -> departments.get(rowIndex).getName();
                case 2 -> departments.get(rowIndex).getDescription();

                default -> null;
            };
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0,columnIndex)!=null){
                return getValueAt(0, columnIndex).getClass();
            }
            return Object.class;
        }


    }

//*****************************Attendance GUI Table Model
    private  static  class  AttendanceTableModel extends AbstractTableModel{
        private  final  List<Attendance>attendances;
        private  final  String[] COLUMNS={"ID","empID","empName","empFatherName","depName","Is Present","Date"};
        private  AttendanceTableModel(List<Attendance> attendances) {
            this.attendances=attendances;
        }
        @Override
        public int getRowCount() {
            return attendances.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }


        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> attendances.get(rowIndex).getId();
                case 1 -> attendances.get(rowIndex).getEmployeeId();
                case 2 -> attendances.get(rowIndex).getEmployeeName();
                case 3 -> attendances.get(rowIndex).getEmployeeFatherName();
                case 4 -> attendances.get(rowIndex).getDepartmentName();
                case 5 -> attendances.get(rowIndex).isPresent();
                case 6 -> attendances.get(rowIndex).getDate();

                default -> null;
            };
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0,columnIndex)!=null){
                return getValueAt(0, columnIndex).getClass();
            }
            return Object.class;
        }


    }

}// End of GUI Class
