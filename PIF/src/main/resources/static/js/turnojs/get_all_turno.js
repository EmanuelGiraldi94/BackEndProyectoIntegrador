window.addEventListener('load', function () {
    (function(){


        const url = '/turnos';
        const settings = {
            method: 'GET'
        }

        fetch(url,settings)
            .then(response => response.json())
            .then(data => {

                for(paciente of data){


                    var table = document.getElementById("pacienteTable");
                    var pacienteRow =table.insertRow();
                    let tr_id = 'tr_' + paciente.id;
                    pacienteRow.id = tr_id;

                    let deleteLink='<a id=\"a_delete_'+paciente.id+'\"'+
                        ' href=\"#\" onclick=\"deleteBy('+paciente.id+')\"'+
                        ' class=\"link-danger\">Borrar</a>';

                    let updateButton = '<button' +
                        ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                        ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                        "Editar" +
                        '</button>';




                    pacienteRow.innerHTML =
                        '<td class=\"td_id\">' + paciente.id + '</td>' +
                        '<td class=\"td_idodontologo\">' + paciente.odontologo.apellido + " " +paciente.odontologo.nombre+ '</td>' +
                        '<td class=\"td_idpaciente\">' + paciente.paciente.apellido + " " + paciente.paciente.nombre + '</td>' +
                        '<td class=\"td_fecha\">' + paciente.fecha+ '</td>' +
                        '<td>'+deleteLink+'</td>'+
                        '<td>'+updateButton+'</td>'



                };

            })
    })

    ()


})