<!DOCTYPE html>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>MyPLS</title>

  <!-- Bootstrap core CSS -->
  <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template -->
  <link href="css/landing-page.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">

  <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>
  <script>
    $(document).ready( function () {
        $('#course-index-table').DataTable();
    } );
  </script>
  <style>
    body{margin-top:20px;}

    .member-details {
        padding-top: 80px;
        padding-bottom: 80px
    }

    @media (min-width:992px) {
        .member-details {
            padding-top: 100px;
            padding-bottom: 100px
        }
    }

    .member-details .member_designation {
        margin-bottom: 30px
    }

    .member-details .member_designation h2 {
        margin-bottom: 5px;
        margin-top: 25px
    }

    @media (min-width:768px) {
        .member-details .member_designation h2 {
            margin-top: 0
        }
    }

    .member-details .member_designation span {
        font-style: italic
    }

    .member-details .member_desc li {
        display: block;
        float: unset;
        width: 100%
    }

    .member-details .member_desc li i {
        color: #0cc652;
        font-size: 14px
    }

    .member-details .member_desc h4 {
        margin-top: 40px
    }

    .member-details .member_desc p {
        margin-top: 10px
    }

    .member-details .member_desc .progress-holder {
        margin-top: 30px
    }

    .member-details .media-box {
        margin-bottom: 20px
    }

    @media (min-width:992px) {
        .member-details .media-box {
            margin-bottom: 0
        }
    }

    .member-details .member_contact {
        padding: 40px;
        position: relative;
        margin-top: 40px
    }

    .member-details .member_contact .media-icon {
        font-size: 32px;
        color: #dae0e6;
        position: relative;
        width: 30px;
        text-align: center;
        float: left;
        margin-right: 15px
    }

    .member-details .member_contact .media-content {
        padding-left: 0;
        float: left
    }

    .member-details .member_contact .media-content h5 {
        font-size: 15px
    }

    .member-details .member_contact .media-content h5,
    .member-details .member_contact .media-content a {
        color: #dae0e6
    }

    @media (min-width:992px) {
        .member-details .member_contact .social-icons {
            text-align: right
        }
    }

    .member-details .member_contact .social-icons .btn-social {
        width: 40px;
        height: 40px;
        line-height: 40px
    }

    .member-details .member_contact .social-icons .btn {
        background-color: transparent;
        border: 1px solid;
        border-color: #999;
        color: #dae0e6
    }

    .member-details .member_contact .social-icons .btn:hover {
        background-color: #0cc652;
        border-color: #0cc652;
        opacity: 1
    }

    .bg-image-holder,
    .bg-image {
        background-size: cover!important;
        background-position: 50% 0!important;
        transition: all .3s linear;
        background: #f5f5f6;
        position: relative
    }

    .bg-image:before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, .7)
    }

    .bg-fixed {
        background-attachment: fixed
    }

    .bg-image .overlay-content {
        position: relative;
        z-index: 5
    }


    .progress-holder {
        margin-top: 50px
    }

    .progress-holder .barWrapper .progressText {
        font-size: 15px;
        color: #222
    }

    .progress-holder .progress {
        margin-bottom: 25px;
        margin-top: 10px;
        overflow: visible;
        background-color: #f5f5f6
    }

    .progress-holder .progress .progress-bar {
        position: relative
    }

    .progress-holder .progress .progress-bar:after {
        position: absolute;
        content: '';
        width: 1px;
        height: 33px;
        background-color: #0cc652;
        top: -8px;
        right: 0;
        z-index: 55
    }

    .img-full {
        width: 100%;
    }

    p {
        color: #8b8e93;
        font-weight: 300;
        margin-bottom: 0;
        font-size: 14px;
        line-height: 26px;
    }


    .styled_list {
        margin-top: 15px;
        position: relative;
        display: inline-block
    }

    @media (min-width:768px) {
        .styled_list {
            margin-top: 15px
        }
    }

    .styled_list li {
        font-size: 14px;
        line-height: 30px
    }

    @media (min-width:768px) {
        .styled_list li {
            font-size: 14px;
            float: left;
            width: 50%
        }
    }

    .styled_list li i {
        margin-right: 10px;
        font-size: 12px
    }

    .styled_list li a {
        color: #8b8e93
    }

    @media (min-width:768px) {
        .styled_list li a {
            font-size: 12px
        }
    }

    @media (min-width:992px) {
        .styled_list li a {
            font-size: 14px
        }
    }

    ol.styled_list {
        margin-left: 15px
    }

    ol.styled_list li {
        padding-left: 10px
    }
  </style>


</head>