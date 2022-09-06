window.addEventListener('load', function () {
    (function(){


      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         for(odontologo of data){

            var table = document.getElementById("odontologoTable");
            var odontologoRow =table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;

            let deleteLink='<a id=\"a_delete_'+odontologo.id+'\"'+
            ' href=\"#\" onclick=\"deleteBy('+odontologo.id+')\"'+
            ' class=\"link-danger\">Borrar</a>';

             let updateButton = '<button' +
                 ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                 ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                 "Editar" +
                 '</button>';





            odontologoRow.innerHTML =
                    '<td class=\"td_id\">' + odontologo.id + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_matricula\">' + odontologo.matricula + '</td>'+
                    '<td>'+deleteLink+'</td>'+
                    '<td>'+updateButton+'</td>'



        };

    })
    })

    ()


    })