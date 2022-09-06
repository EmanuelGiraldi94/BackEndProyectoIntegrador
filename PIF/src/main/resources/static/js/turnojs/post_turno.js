window.addEventListener('load', function () {


    const formulario = document.querySelector('#add_new_paciente');


    formulario.addEventListener('submit', function (event) {


        const formData1 = {


            odontologo:{


                id: document.querySelector('#idodontologo').value,

            },

            paciente:{


                id: document.querySelector('#idpaciente').value,

            },


            fecha: document.querySelector('#fecha').value,


        };

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',

            },
            body: JSON.stringify(formData1)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close"' +
                    ' data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Paciente agregado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();

            })

            .catch(error => {

                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";

                resetUploadForm();})
    });



});