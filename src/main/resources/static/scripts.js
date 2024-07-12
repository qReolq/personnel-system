// add-department
$.ajax({
    url: 'http://localhost:8080/api/v1/departments',
    dataType: 'json',
    success: function (data) {
        const departments = data.map(function (department) {
            return {
                id: department.id,
                text: department.name
            };
        });

        $('#parentDepartmentId').select2({
            placeholder: 'Select a parent department',
            data: departments,
            allowClear: true
        });
    },
    error: function (error) {
        console.error('Error loading departments:', error);
    }
});

$("#add-department-form").on("submit", function (event) {

    let data = $(this).serializeArray();
    let url = `http://localhost:8080/api/v1/departments`;

    if (data[1] != null) {
        let parentId = data[1].value;
        url = `http://localhost:8080/api/v1/departments?parentDepartmentId=${parentId}`;
    }

    data = JSON.stringify({name: data[0].value});
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: data
    })
        .then(response => response.json())
        .then(data => {
            alert('Department added successfully')
            console.log(data)
        });
});

// add-employee

$.ajax({
    url: 'http://localhost:8080/api/v1/departments',
    dataType: 'json',
    success: function (data) {
        const departments = data.map(function (department) {
            return {
                id: department.id,
                text: department.name
            };
        });

        $('#departmentId').select2({
            placeholder: 'Select a department',
            data: departments,
            allowClear: true
        });
    },
    error: function (error) {
        console.error('Error loading departments:', error);
    }
});

$("#add-employee-form").on("submit", function (event) {
    event.preventDefault();

    let data = $(this).serializeArray();
    let url = `http://localhost:8080/api/v1/employees/hire`;

    if (data[1] != null) {
        let parentId = data[1].value;
        url = `http://localhost:8080/api/v1/employees/hire?departmentId=${parentId}`;
    }

    data = JSON.stringify({fullName: data[0].value});
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: data
    })
        .then(response => response.json())
        .then(data => {
            alert('Employee added successfully')
            console.log(data)
        });
});

// Departments on Date

$(function () {
    $('input[name="date"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        autoApply: true
    }, function (start, end, label) {
        let formattedStart = start.format('YYYY-MM-DD');
        let url = `http://localhost:8080/api/v1/departments/all?date=${formattedStart}`

        fetch(url)
            .then(response => response.json())
            .then(data => {
                const content = document.getElementById('content');
                content.innerHTML = '';

                data.forEach(department => {
                    content.appendChild(displayDepartment(department));
                });
            });

    });
});

function displayDepartment(department) {
    const departmentDiv = document.createElement('div');
    departmentDiv.className = 'department';

    const title = document.createElement('h2');
    title.textContent = department.name;
    departmentDiv.appendChild(title);

    const id = document.createElement('p');
    id.textContent = `ID: ${department.id}`;
    departmentDiv.appendChild(id);

    if (department.parentDepartment !== null) {
        const parent = document.createElement('p');
        parent.textContent = `Parent Department ID: ${department.parentDepartment}`;
        departmentDiv.appendChild(parent);
    }

    const subDeptTitle = document.createElement('h3');
    subDeptTitle.textContent = 'Sub Departments:';
    departmentDiv.appendChild(subDeptTitle);

    const subDeptList = document.createElement('ul');
    department.subDepartments.forEach(subDeptId => {
        const li = document.createElement('li');
        li.textContent = subDeptId;
        subDeptList.appendChild(li);
    });
    departmentDiv.appendChild(subDeptList);

    const employeeTitle = document.createElement('h3');
    employeeTitle.textContent = 'Employees:';
    departmentDiv.appendChild(employeeTitle);

    const employeeList = document.createElement('ul');
    department.employees.forEach(employee => {
        const li = document.createElement('li');
        li.className = 'employee';
        li.textContent = `${employee.fullName} (ID: ${employee.id})`;
        employeeList.appendChild(li);
    });
    departmentDiv.appendChild(employeeList);

    const createAt = document.createElement('p');
    createAt.textContent = `Created At: ${new Date(department.createAt).toLocaleString()}`;
    departmentDiv.appendChild(createAt);

    return departmentDiv;
}

function daterangepickerConfig() {
    let datefilter = $('input[name="range"]');

    datefilter.daterangepicker({
        autoUpdateInput: false,
        locale: {
            cancelLabel: 'Clear'
        },
        autoApply: true
    });

    datefilter.on('apply.daterangepicker', async (ev, picker) => {
        $('#range').val(picker.startDate.format('YYYY-MM-DD') + ' ' + picker.endDate.format('YYYY-MM-DD'));
    });

}
daterangepickerConfig();

$('#employees-in-department-form').on('submit', function (event) {
    event.preventDefault();

    let data = $(this).serializeArray();
    let departmentId = data[0].value;
    let [start, end] = data[1].value.split(' ');

    let url = `http://localhost:8080/api/v1/employees/by-department?startDate=${start}&endDate=${end}&departmentId=${departmentId}`

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const content = document.getElementById('content');
            content.innerHTML = '';
            data.forEach(record => {
                content.appendChild(displayRecord(record));
            });
        });

});

function displayRecord(record) {
    const recordDiv = document.createElement('div');
    recordDiv.className = 'record';

    const title = document.createElement('h2');
    title.textContent = `Record ID: ${record.id}`;
    recordDiv.appendChild(title);

    const hiredDate = document.createElement('p');
    hiredDate.textContent = `Hired Date: ${new Date(record.hiredDate).toLocaleString()}`;
    recordDiv.appendChild(hiredDate);

    if (record.firedDate) {
        const firedDate = document.createElement('p');
        firedDate.textContent = `Fired Date: ${new Date(record.firedDate).toLocaleString()}`;
        recordDiv.appendChild(firedDate);
    } else {
        const firedDate = document.createElement('p');
        firedDate.textContent = `Fired Date: Currently Employed`;
        recordDiv.appendChild(firedDate);
    }

    const employeeTitle = document.createElement('h3');
    employeeTitle.textContent = 'Employee Information:';
    recordDiv.appendChild(employeeTitle);

    const employeeInfo = document.createElement('div');
    employeeInfo.className = 'employee-info';

    const employeeName = document.createElement('p');
    employeeName.textContent = `Name: ${record.employee.fullName}`;
    employeeInfo.appendChild(employeeName);

    const employeeId = document.createElement('p');
    employeeId.textContent = `Employee ID: ${record.employee.id}`;
    employeeInfo.appendChild(employeeId);

    const employeeDept = document.createElement('p');
    employeeDept.textContent = `Current department ID: ${record.employee.department ? record.employee.department : 'N/A'}`;
    employeeInfo.appendChild(employeeDept);

    recordDiv.appendChild(employeeInfo);

    const department = document.createElement('p');
    department.textContent = `Department ID: ${record.department}`;
    recordDiv.appendChild(department);

    return recordDiv;
}