$(document).ready(function () {

    $('.table .eBtn').on('click',function (event) {
        event.preventDefault();
        var href = $(this).attr('href');



        $.get(href,function (user) {
            $('.myForm #iid').val(user.id);
            $('.myForm #iusername').val(user.username);
            $('.myForm #iage').val(user.age);
            $('.myForm #iemail').val(user.email);

        });
        $('.myForm #EditModal').modal();
    });

    $('.table .delBtn').on('click',function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href,function (user) {
            $('.myForm #did').val(user.id);
            $('.myForm #dusername').val(user.username);
            $('.myForm #dage').val(user.age);
            $('.myForm #demail').val(user.email);

        });
        $('.myForm #deleteModal').modal();
    });

   /* $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);

        $('#deleteModal').modal();
    });*/

});