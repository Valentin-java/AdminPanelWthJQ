$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click',function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if (text == 'Edit') {

            $.get(href,function (user, status) {

                $('.myForm #username').val(user.username);
                $('.myForm #age').val(user.age);
                $('.myForm #email').val(user.email);
                $('.myForm #password').val(user.password);
                $('.myForm #confirmPassword').val(user.password);
            });
            $('.myForm #EditModal').modal();
        } else {

            $('.myForm #username').val('');
            $('.myForm #age').val('');
            $('.myForm #email').val('');
            $('.myForm #password').val('');
            $('.myForm #confirmPassword').val('');
            $('.myForm #EditModal').modal();
        }
    });

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();
    });

});

