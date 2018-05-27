$(document).ready( function () {
   let teamTable = '';
    const createTeamTable = () => {
	    teamTable = $('#table_id').DataTable( {
	    	"processing": true,
	    	 retrieve: true,
	        "ajax": "http://localhost:8080/get-teams",
	        "columns": [
	            { "data": "teamName" },
	            { "data": "homeGround" }
	        ]
	    } );
    };
    createTeamTable();

    $('#save_team').click(function(){
    	const teamName = $('#team_name').val();
    	const homeGround = $('#home_ground').val();
    	$.ajax({
    		url: 'http://localhost:8080/MatchScheduler/addteam',
    		type: 'POST',
    		dataType: 'json',
    		data: {
    			teamName: teamName,
    			homeGround: homeGround
    		},
    		success: function(){
    			$('#team_name').val('');
    			$('#home_ground').val('');
    			$('#close_team_popup').click();
    			teamTable.destroy();
    			createTeamTable();
    		},
    		error: function(err){
    			alert(err)
    		}
    	})
    })
} );