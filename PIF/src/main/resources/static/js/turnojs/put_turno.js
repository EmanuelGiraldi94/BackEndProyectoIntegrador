window.addEventListener('load', function () {


    const formulario = document.querySelector('#update_odontologo_form');
    formulario.addEventListener('submit', function (event) {
        let ododontologo_id = document.querySelector('#odontologo_id').value;



        const formData = {


            id: document.querySelector('#odontologo_id').value,
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
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }


        fetch(url,settings)
            .then(response => response.json())

    })
})



function findBy(id) {
    const url = '/turnos'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;


            document.querySelector('#odontologo_id').value = paciente.id;

            document.querySelector('#fecha').value = paciente.fecha;
            document.querySelector('#idpaciente').value = paciente.paciente.id;
            document.querySelector('#idodontologo').value = paciente.odontologo.id;



            document.querySelector('#div_odontologo_updating').style.display = "block";

        }).catch(error => {
        alert("Error: " + error);
    })
}