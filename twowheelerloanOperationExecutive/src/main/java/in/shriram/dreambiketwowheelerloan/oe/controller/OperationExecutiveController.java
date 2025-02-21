package in.shriram.dreambiketwowheelerloan.oe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shriram.dreambiketwowheelerloan.oe.servicei.OperationExecutiveServicei;

@RestController
@RequestMapping("/oe")
public class OperationExecutiveController {

	@Autowired
	OperationExecutiveServicei oes;
}
