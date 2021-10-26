$(document).ready(function () {

    $('.table .eBtn').on('click',function (event) {
        event.preventDefault();
        var href = $(this).attr('href');



            $.get(href,function (user) {
                $('.myForm #id').val(user.id);
                $('.myForm #username').val(user.username);
                $('.myForm #age').val(user.age);
                $('.myForm #email').val(user.email);

            });
            $('.myForm #EditModal').modal();
    });

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);

        $('#deleteModal').modal();
    });

});

