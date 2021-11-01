<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . 'libraries/REST_Controller.php';

Class Menu extends REST_Controller {

    public function __construct($config = 'rest') {
        parent::__construct($config);
        $this->load->model("restmodel/Menu_model");
    }

    function create_response($data, $message) {
        $response = array('data' => $data, 'message' => $message);
        echo json_encode($response);
    }

//`menu_id`, `shop_id`, `menu_name`, `is_valid`
    public function create_post() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $user_register_array = array(
            'shop_id' => $incoming_json->shopid,
            'menu_name' => $incoming_json->menuename,
            'is_valid' => $incoming_json->is_valid
        );

        $result = $this->Menu_model->create('tbl_menu_header', $user_register_array);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "MENU Creation Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "MENU reation failed"), 422);
        }
    }

    public function create_menu_post() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $menu_creation_array = array(
            'item_id' => $incoming_json->items,
            'menu_id' => $incoming_json->menu_id
        );

        $result = $this->Menu_model->create('tbl_menu_details', $menu_creation_array);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "MENU Creation Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "MENU reation failed"), 422);
        }
    }

    public function update_put() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $update_menu_array = array(
            'is_valid' => $incoming_json->is_valid
        );

        $where = array("menu_id" => $incoming_json->mid);
        $result = $this->Menu_model->update('tbl_menu_header', $update_menu_array, $where);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "MENU Update Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "MENU Update failed"), 422);
        }
    }

    public function load_get() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $result = $this->Menu_model->get_menu_information($incoming_json->mid);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "MENU INFO"), 200);
        } else {
            $this->response($this->create_response(array(), "Failed to load MENU information"), 422);
        }
    }

    public function remove_delete() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $where = array("menu_id" => $incoming_json->mid);
        $result = $this->Menu_model->remove('tbl_menu_header', $where);
         $result_2 = $this->Menu_model->remove('tbl_menu_details', $where);
//        var_dump($result);
        if ($result != NULL && $result != FALSE && $result_2!=FALSE) {
            $this->response($this->create_response($result, "MENU Delete Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "MENU Delete failed"), 422);
        }
    }

}
