<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title></title>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous">
	
</script>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">


<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>


<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

<style>
#datepicker1{
 width: 80px;
 font-size: 12px;
}
.margin-top40 {
 margin-top: 40px;
}
.select-date {
 padding-left: 15px;
}
</style>
</head>
<body>
	<div class="container">
		<h1>IPL Match Scheduler</h1>
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="jumbotron">
					<p>Please add your team and home ground.</p>
					<p>

						<button type="button" class="btn btn-primary btn-lg"
							data-toggle="modal" data-target="#myModal">Add</button>
					</p>
					<!-- Modal -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Please fill to
										add</h4>
								</div>
								<div class="modal-body">
									<label>Team name</label> <input type="text" id="team_name"
										name="team_name"> <br /> <br /> <label>Home
										ground</label> <input type="text" id="home_ground" name="home_ground">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										id="close_team_popup" data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary" id="save_team">Save</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-6 col-md-6">
				<table id="table_id" class="display">
					<thead>
						<tr>
							<th>Team_Id</th>
							<th>Team</th>
							<th>Home ground</th>
							<th>Action</th>
						</tr>
					</thead>

				</table>
			</div>
		</div>
		<div class="row well margin-top40">
		   <div class="col-lg-6 col-md-6">
		     	<div class="jumbotron">
			     <h3>Schedule Match</h3>
			     <div class="row">
			      <h4 class="select-date">Select the date</h4>
			      <div class="col-lg-5 col-md-5">
			       <p>From: <input type="text" id="datepicker1"></p>
			      </div>
			      
			      
			     </div>
			     <p><a class="btn btn-success btn-lg" role="button" id="schedule">Schedule</a></p>
			   </div>
		  </div>
		  	<div class="col-lg-6 col-md-6">
				<table id="match_table" class="display">
					<thead>
						<tr>
							<th>Date</th>
							<th>Match</th>
							<th>Venue</th>
							
						</tr>
					</thead>

				</table>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var teamTable = "";
		$(document).ready( function () {
			
			    var createTeamTable = function()  {
				teamTable = $('#table_id').DataTable( {
				    	"processing": true,
				    	 retrieve: true,
				        "ajax": "http://localhost:8080/MatchScheduler/getTeams",
				        "columns": [
				        	{ "data": "id" },
				            { "data": "teamName" },
				            { "data": "homeGround"},
				            { 
				                data: null,
				                defaultContent: '<button class="btn-delete" type="button">Delete</button>'
				            }
				           
				        ]
				    } );
			    };
			  
			   
			   
			    createTeamTable();
			    

			    $('#save_team').click(function(){
			    	const teamName = $('#team_name').val();
			    	const homeGround = $('#home_ground').val();
			    	console.log("before ajax");
			    	$.ajax({
			    		url: 'http://localhost:8080/MatchScheduler/addTeam',
			    		type: 'POST',
			    		cache:false,
			    		data: {
			    			"teamName": teamName,
			    			"homeGround": homeGround
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
			    $('#table_id').on('click', '.btn-delete', function(){
			        var row = this.closest('tr');
			        var temp = teamTable.row( row ).data();
			        var id = temp.id;
			        console.log("--------------ID",id);
			        teamTable.row(row).remove().draw(false);
			        $.ajax({
			    		url: 'http://localhost:8080/MatchScheduler/removeTeam',
			    		type: 'POST',
			    		cache:false,
			    		data: {
			    			"id": id
			    			
			    		},
			    		success: function(response){
			    			alert(response)
			    		},
			    		error: function(err){
			    			alert(err)
			    		}
			    	})
			       
			     });

			    /* date picker */
			    $( "#datepicker1" ).datepicker({ dateFormat: "dd-mm-yy" });

			    $('#schedule').click(function() { 
				    const fromDate = $( "#datepicker1" ).val();
				    console.log(fromDate);
				    $.ajax({
				      url: 'http://localhost:8080/MatchScheduler/createSchedules',
				      type: 'POST',
				      data: {
				       fromDate: fromDate
				      },
				      success: function(result) {
				       console.log(result);
				       createMatchTable();
				      },
				      error: function(err) {
				       alert(err);
				      }
				    });
				}); 
			    
			    var createMatchTable = function()  {
					teamTable = $('#match_table').DataTable( {
				    	"processing": true,
				    	 retrieve: true,
				        "ajax": "http://localhost:8080/MatchScheduler/getSchedules",
				        "columns": [
				        	{ "data": "matchDate" },
				            { "data": "match" },
				            { "data": "venue"},
				            
				        ]
				    } );
			    };
			    createMatchTable();
			});
		
	</script>
</body>
</html>