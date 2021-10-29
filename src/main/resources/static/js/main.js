$(document).ready(function () {

    $(document).on('click', '.eBtn', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');

        $.get(href,function (user) {
            $('.myForm #iid').val(user.id);
            $('.myForm #iusername').val(user.username);
            $('.myForm #iage').val(user.age);
            $('.myForm #iemail').val(user.email);

        });
        $('.myForm #EditModal').modal();
    });

    $(document).on('click', '.delBtn',function (event) {
        event.preventDefault();
        let href = $(this).attr('href');

        $.get(href,function (user) {
            $('.myForm #did').val(user.id);
            $('.myForm #dusername').val(user.username);
            $('.myForm #dage').val(user.age);
            $('.myForm #demail').val(user.email);

        });
        $('.myForm #deleteModal').modal();
    });

    $.getJSON('http://localhost:8080/admin/findAll/', function(json_data){

        let table_obj = $('#table');
        $.each(json_data, function(index, user){
            let table_row = $('<tr>', {});
            let table_cell1 = $('<td>', {html: user.id});
            let table_cell2 = $('<td>', {html: user.username});
            let table_cell3 = $('<td>', {html: user.age});
            let table_cell4 = $('<td>', {html: user.email});
            let table_cell5 = $('<td>', {html: user.roleList});
            let table_cell6 = $('<td>', {html: '<a class="btn btn-info eBtn" href="findOne/' + user.id + '">Edit</a>'});
            let table_cell7 = $('<td>', {html: '<a class="btn btn-danger delBtn" href="findOne/' + user.id + '">Delete</a>'});
     table_row.append(table_cell1,table_cell2,table_cell3,table_cell4,table_cell5,table_cell6,table_cell7);
     table_obj.append(table_row);
        });

    });






});