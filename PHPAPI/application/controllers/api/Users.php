<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . 'libraries/REST_Controller.php';

Class Users extends REST_Controller {

    public function __construct($config = 'rest') {
        parent::__construct($config);
        $this->load->model("restmodel/User_model");
    }

    function create_response($data, $message) {
        $response = array('data' => $data, 'message' => $message);
        echo json_encode($response);
    }

    public function create_post() {
        $incoming_json = json_decode(file_get_contents("php://input"));
//        var_dump($incoming_json);
        $user_register_array = array(
            'username' => $incoming_json->username,
            'password' => $incoming_json->password,
            'type'=>$incoming_json->type,
        );

        $result = $this->User_model->create('tbl_users', $user_register_array);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "User Registration Successfull"), 200);
        } else {
            $this->response($this->create_response(array(), "User Registration Failed"), 422);
        }
    }

    public function update_put() {
        $json = json_decode(file_get_contents("php://input"));
    }

    public function load_get() {
        $json = json_decode(file_get_contents("php://input"));
    }

    public function remove_delete() {
        $json = json_decode(file_get_contents("php://input"));
    }
    
    public function login_post(){
        
                $incoming_json = json_decode(file_get_contents("php://input"));
//        var_dump($incoming_json);
        $user_register_array = array(
            'username' => $incoming_json->username,
            'password' => $incoming_json->password,
        );

        $result = $this->User_model->access_request($user_register_array);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "Login Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "Login failed  please check your username and password"), 403);
        }
    }

}
