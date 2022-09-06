window.addEventListener('load', function () {
    (function(){


        const url = '/pacientes';
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
                        '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                        '<td class=\"td_email\">' + paciente.email + '</td>'+
                        '<td class=\"td_dnia\">' + paciente.dni + '</td>'+
                        '<td class=\"td_fechaIngreso\">' + paciente.fechaIngreso + '</td>'+
                        '<td class=\"td_calle\">' + paciente.domicilio.calle + '</td>'+
                        '<td class=\"td_numero\">' + paciente.domicilio.numero + '</td>'+
                        '<td class=\"td_localidad\">' + paciente.domicilio.localidad + '</td>'+
                        '<td class=\"td_provincia\">' + paciente.domicilio.provincia + '</td>'+
                        '<td>'+deleteLink+'</td>'+
                        '<td>'+updateButton+'</td>'



                };

            })
    })

    ()


})